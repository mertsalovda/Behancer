package com.elegion.test.behancer.ui.userprojects;

import com.elegion.test.behancer.BuildConfig;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.project.Project;
import com.elegion.test.behancer.data.model.project.ProjectResponse;
import com.elegion.test.behancer.ui.BaseProjectsViewModel;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.utils.ApiUtils;

import java.util.ArrayList;

import io.reactivex.schedulers.Schedulers;

public class UserProjectsViewModel extends BaseProjectsViewModel {

    private String mUserName;

    public UserProjectsViewModel(Storage storage, ProjectsAdapter.OnItemClickListener onItemClickListener, String name) {
        super(storage, onItemClickListener);
        mUserName = name;
//        mProjects = storage.getProjectsPaged();
        mProjects = storage.getUserProjectsPaged(mUserName);
        updateProjects();
    }

    @Override
    protected void updateProjects() {
        mDisposable = ApiUtils.getApiService()
                .getProjects(BuildConfig.API_QUERY)
                .map(ProjectResponse::getProjects)
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doFinally(() -> mIsLoading.postValue(false))
                .doOnSuccess(response -> mIsErrorVisible.postValue(false))
                .subscribeOn(Schedulers.io())
                .subscribe(
                        response -> {
                            mStorage.insertProjects(response);
                        },
                        throwable -> {
                            mIsErrorVisible.postValue(true);
                            boolean value = mProjects.getValue() == null || mProjects.getValue().size() == 0;
                            mIsErrorVisible.postValue(value);
                        });

    }
}

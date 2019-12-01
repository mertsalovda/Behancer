package com.elegion.test.behancer.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.support.v4.widget.SwipeRefreshLayout;

import com.elegion.test.behancer.BuildConfig;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.project.ProjectResponse;
import com.elegion.test.behancer.data.model.project.RichProject;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseProjectsViewModel extends ViewModel {

    protected ProjectsAdapter.OnItemClickListener mOnItemClickListener;

    protected Disposable mDisposable;
    protected Storage mStorage;
    protected MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<>();
    protected MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    protected LiveData<PagedList<RichProject>> mProjects;
    protected SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = this::updateProjects;

    public BaseProjectsViewModel(Storage storage, ProjectsAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        mStorage = storage;
    }

    protected void updateProjects() {
        mDisposable = ApiUtils.getApiService()
                .getProjects(BuildConfig.API_QUERY)
                .map(ProjectResponse::getProjects)
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doFinally(() -> mIsLoading.postValue(false))
                .doOnSuccess(response -> mIsErrorVisible.postValue(false))
                .subscribeOn(Schedulers.io())
                .subscribe(
                        response -> mStorage.insertProjects(response),
                        throwable -> {
                            mIsErrorVisible.postValue(true);
                            boolean value = mProjects.getValue() == null || mProjects.getValue().size() == 0;
                            mIsErrorVisible.postValue(value);
                        });

    }

    @Override
    public void onCleared() {
        mStorage = null;
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public MutableLiveData<Boolean> getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public LiveData<PagedList<RichProject>> getProjects() {
        return mProjects;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }

    public ProjectsAdapter.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(ProjectsAdapter.OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    };
}

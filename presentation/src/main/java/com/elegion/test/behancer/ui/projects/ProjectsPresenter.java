package com.elegion.test.behancer.ui.projects;

import com.elegion.data.BuildConfig;
import com.elegion.data.Storage;
import com.elegion.data.api.BehanceApi;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.utils.ApiUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProjectsPresenter extends BasePresenter {

    private ProjectsView mView;
    @Inject
    BehanceApi mApi;
    @Inject
    Storage mStorage;

    @Inject
    public ProjectsPresenter() {
    }

    public void setView(ProjectsView view) {
        mView = view;
    }

    public void getProjects() {
        mCompositeDisposable.add(mApi.getProjects(BuildConfig.API_QUERY)
                .doOnSuccess(response -> mStorage.insertProjects(response))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getProjects() : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mView.showRefresh())
                .doFinally(() -> mView.hideRefresh())
                .subscribe(
                        response -> mView.showProjects(response.getProjects()),
                        throwable -> mView.showError()));

    }

    public void openProfileFragment(String username) {
        mView.showProfileFragment(username);
    }


}

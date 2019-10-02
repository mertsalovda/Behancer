package com.elegion.test.behancer.ui.profile.userprojects;

import com.arellomobile.mvp.InjectViewState;
import com.elegion.test.behancer.BuildConfig;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.data.model.user.UserResponse;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class UserProjectsPresenter extends BasePresenter<UserProjectsView> {

    private Storage mStorage;

    public UserProjectsPresenter(Storage mStorage) {
        this.mStorage = mStorage;
    }

    public void getProjects(String user) {
        mCompositeDisposable.add(ApiUtils.getApiService()
                .getUserProjects(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showRefresh())
                .doFinally(() -> getViewState().hideRefresh())
                .subscribe(
                        responses -> getViewState().showProjects(responses.getProjects()),
                        throwable -> getViewState().showError()));

    }

}

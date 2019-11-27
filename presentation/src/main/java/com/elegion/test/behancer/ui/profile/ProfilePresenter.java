package com.elegion.test.behancer.ui.profile;

import com.elegion.domain.service.ProfileService;
import com.elegion.test.behancer.common.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfilePresenter extends BasePresenter {

    private ProfileView mView;

    @Inject
    ProfileService mService;

    @Inject
    public ProfilePresenter() {
    }

    public void setView(ProfileView view) {
        mView = view;
    }

    public void getProfile(String username){
        mCompositeDisposable.add(mService.getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mView.showRefresh())
                .doFinally(() -> mView.hideRefresh())
                .subscribe(
                        response -> mView.showProfile(response),
                        throwable -> mView.showError()));
    }
}

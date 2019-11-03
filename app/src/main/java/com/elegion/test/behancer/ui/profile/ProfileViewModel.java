package com.elegion.test.behancer.ui.profile;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.utils.ApiUtils;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel {
    private Storage mStorage;
    private Disposable mDisposable;
    private String mUsername;
    private ObservableBoolean mIsErrorVisible = new ObservableBoolean(false);
    private ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private ObservableField<User> mUser = new ObservableField<>();
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = this::loadProfile;

    private View.OnClickListener mOnClickListener;

    public ProfileViewModel(Storage storage, View.OnClickListener listener) {
        mStorage = storage;
        mOnClickListener = listener;
    }

    public void loadProfile() {
        mDisposable = ApiUtils.getApiService()
                .getUserInfo(mUsername)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> mStorage.insertUser(response))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                                mStorage.getUser(mUsername) :
                                null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.set(true))
                .doFinally(() -> mIsLoading.set(false))
                .subscribe(
                        response -> {
                            mIsErrorVisible.set(false);
                            mUser.set(response.getUser());
                        },
                        throwable -> mIsErrorVisible.set(true));
    }

    public void dispatchDetach() {
        mStorage = null;
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public ObservableBoolean getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public ObservableField<User> getUser() {
        return mUser;
    }

    public View.OnClickListener getOnClickListener() {
        return mOnClickListener;
    }
}

package com.elegion.test.behancer.ui.profile;

import android.databinding.ObservableBoolean;
import android.support.v4.widget.SwipeRefreshLayout;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.databinding.ProfileBinding;
import com.elegion.test.behancer.utils.ApiUtils;

import java.lang.ref.WeakReference;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel {
    private Storage mStorage;
    private Disposable mDisposable;
    private String mUsername;
    private ObservableBoolean mIsErrorVisible = new ObservableBoolean(false);
    private ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = this::loadProfile;

    private WeakReference<ProfileBinding> mProfileBinding;

    public ProfileViewModel(Storage storage) {
        mStorage = storage;
    }

    public void loadProfile() {
        mDisposable = ApiUtils.getApiService().getUserInfo(mUsername)
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
                            mProfileBinding.get().setUser(response.getUser()); // Устанавливаю User в ProfileBinding
                            mProfileBinding.get().invalidateAll(); // Обновляю отображение
                        },
                        throwable -> mIsErrorVisible.set(true));
    }

    public void dispatchDetach() {
        mStorage = null;
        mProfileBinding.clear();
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

    public void setBinding(ProfileBinding binding){
        mProfileBinding = new WeakReference<>(binding);
    }
}

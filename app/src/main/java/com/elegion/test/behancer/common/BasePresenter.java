package com.elegion.test.behancer.common;

import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V extends BaseView> extends MvpPresenter<V> {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable(); // Чтобы отписываться от всех подписок

    public void disposeAll(){
        mCompositeDisposable.clear();
    }
}

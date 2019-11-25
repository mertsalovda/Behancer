package com.elegion.test.behancer.common;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable(); // Чтобы отписываться от всех подписок

    public void disposeAll(){
        mCompositeDisposable.clear();
    }
}

package com.elegion.test.behancer.common;

import com.arellomobile.mvp.MvpAppCompatFragment;

public abstract class PresenterFragment extends MvpAppCompatFragment {

    public abstract BasePresenter getPresenter();

    @Override
    public void onDetach() {
        if (getPresenter() != null){
            getPresenter().disposeAll();
        }
        super.onDetach();
    }
}

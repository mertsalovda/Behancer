package com.elegion.test.behancer.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.projects.ProjectsViewModel;
import com.elegion.test.behancer.ui.userprojects.UserProjectsViewModel;

public class CustomFactory extends ViewModelProvider.NewInstanceFactory {

    private Storage mStorage;
    protected ProjectsAdapter.OnItemClickListener mOnItemClickListener;
    private String mUserName;

    public CustomFactory(Storage storage, ProjectsAdapter.OnItemClickListener onItemClickListener, String name) {
        mStorage = storage;
        mOnItemClickListener = onItemClickListener;
        mUserName = name;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return mUserName == null
                ? (T) new ProjectsViewModel(mStorage, mOnItemClickListener)
                : (T) new UserProjectsViewModel(mStorage, mOnItemClickListener, mUserName);
    }
}

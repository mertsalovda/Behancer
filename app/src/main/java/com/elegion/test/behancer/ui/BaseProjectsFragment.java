package com.elegion.test.behancer.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.databinding.ProjectsBinding;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.projects.ProjectsViewModel;
import com.elegion.test.behancer.utils.CustomFactory;

public abstract class BaseProjectsFragment extends Fragment {

    protected BaseProjectsViewModel mViewModel;
    protected ProjectsAdapter.OnItemClickListener mOnItemClickListener;
    protected String mUserName;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
            Storage storage = ((Storage.StorageOwner) context).obtainStorage();
            mOnItemClickListener = setOnItemClickListenerFomAdapter();
            mUserName = setUserName();
            onCreateViewModel(storage, mOnItemClickListener, mUserName);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ProjectsBinding binding = ProjectsBinding.inflate(inflater, container, false);
        binding.setVm(mViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void onCreateViewModel(Storage storage, ProjectsAdapter.OnItemClickListener onItemClickListener, String name) {
        CustomFactory factory = new CustomFactory(storage, onItemClickListener, name);
        mViewModel = ViewModelProviders.of(this, factory).get(ProjectsViewModel.class);
        mViewModel.setOnItemClickListener(onItemClickListener);
    }

    protected abstract ProjectsAdapter.OnItemClickListener setOnItemClickListenerFomAdapter();

    protected abstract String setUserName();
}

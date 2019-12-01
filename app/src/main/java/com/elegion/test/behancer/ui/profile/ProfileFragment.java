package com.elegion.test.behancer.ui.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.elegion.test.behancer.R;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.databinding.ProfileBinding;
import com.elegion.test.behancer.ui.userprojects.UserProjectsActivity;

/**
 * Created by Vladislav Falzan.
 */

public class ProfileFragment extends Fragment {

    public static final String PROFILE_KEY = "PROFILE_KEY";

    private String mUsername;

    private Button mBtnUserProjects;

    private ProfileViewModel mProfileViewModel;
    private View.OnClickListener mOnClickListener = v -> {
        Bundle args = new Bundle();
        args.putString(PROFILE_KEY, mUsername);
        Intent intent = new Intent(v.getContext(), UserProjectsActivity.class);
        intent.putExtras(args);
        startActivity(intent);
    };

    public static ProfileFragment newInstance(Bundle args) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
            Storage storage = ((Storage.StorageOwner) context).obtainStorage();
            mProfileViewModel = new ProfileViewModel(storage, mOnClickListener);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ProfileBinding binding = ProfileBinding.inflate(inflater, container, false);
        binding.setVm(mProfileViewModel);
        mBtnUserProjects = binding.getRoot().findViewById(R.id.btn_all_projects);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            mUsername = getArguments().getString(PROFILE_KEY);
        }

        if (getActivity() != null) {
            getActivity().setTitle(mUsername);
            mProfileViewModel.setUsername(mUsername);
        }

        mProfileViewModel.loadProfile();

        mBtnUserProjects.setOnClickListener(mOnClickListener);
    }

    @Override
    public void onDetach() {
        mProfileViewModel.dispatchDetach();
        mOnClickListener = null;
        super.onDetach();
    }
}

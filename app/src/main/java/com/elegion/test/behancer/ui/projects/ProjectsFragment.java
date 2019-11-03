package com.elegion.test.behancer.ui.projects;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.elegion.test.behancer.ui.BaseProjectsFragment;
import com.elegion.test.behancer.ui.profile.ProfileActivity;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.utils.CustomFactory;

/**
 * Created by Vladislav Falzan.
 */

public class ProjectsFragment extends BaseProjectsFragment {

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    private ProjectsAdapter.OnItemClickListener mOnItemClickListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
            Storage storage = ((Storage.StorageOwner) context).obtainStorage();
            CustomFactory factory = new CustomFactory(storage, mOnItemClickListener, null);
            mViewModel = ViewModelProviders.of(this, factory).get(ProjectsViewModel.class);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mOnItemClickListener = username -> {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            Bundle args = new Bundle();
            args.putString(ProfileFragment.PROFILE_KEY, username);
            intent.putExtra(ProfileActivity.USERNAME_KEY, args);
            startActivity(intent);
        };

        mViewModel.setOnItemClickListener(mOnItemClickListener);
    }

    @Override
    public void onDetach() {
        mViewModel.setOnItemClickListener(null);
        super.onDetach();
    }
}

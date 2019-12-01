package com.elegion.test.behancer.ui.projects;

import android.content.Intent;
import android.os.Bundle;

import com.elegion.test.behancer.ui.BaseProjectsFragment;
import com.elegion.test.behancer.ui.profile.ProfileActivity;
import com.elegion.test.behancer.ui.profile.ProfileFragment;

public class ProjectsFragment extends BaseProjectsFragment {

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    @Override
    protected ProjectsAdapter.OnItemClickListener setOnItemClickListenerFomAdapter() {
        return username -> {
            Intent intent = new Intent(this.getActivity(), ProfileActivity.class);
            Bundle args = new Bundle();
            args.putString(ProfileFragment.PROFILE_KEY, username);
            intent.putExtra(ProfileActivity.USERNAME_KEY, args);
            startActivity(intent);
        };
    }

    @Override
    protected String setUserName() {
        return null;
    }
}

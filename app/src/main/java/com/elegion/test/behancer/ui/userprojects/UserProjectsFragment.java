package com.elegion.test.behancer.ui.userprojects;

import com.elegion.test.behancer.ui.BaseProjectsFragment;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;

public class UserProjectsFragment extends BaseProjectsFragment {

    public static UserProjectsFragment newInstance() {
        return new UserProjectsFragment();
    }

    @Override
    protected ProjectsAdapter.OnItemClickListener setOnItemClickListenerFomAdapter() {
        return null;
    }

    @Override
    protected String setUserName() {
        return getActivity().getIntent().getExtras().getString(ProfileFragment.PROFILE_KEY);
    }


}

package com.elegion.test.behancer.ui.userprojects;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.ui.BaseProjectsFragment;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.utils.CustomFactory;

public class UserProjectsFragment extends BaseProjectsFragment {

    public static UserProjectsFragment newInstance() {
        return new UserProjectsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        String name = getActivity().getIntent().getExtras().getString(ProfileFragment.PROFILE_KEY);
        if (context instanceof Storage.StorageOwner) {
            Storage storage = ((Storage.StorageOwner) context).obtainStorage();
            CustomFactory factory = new CustomFactory(storage, null, name);
            mViewModel = ViewModelProviders.of(this, factory).get(UserProjectsViewModel.class);
        }
    }
}

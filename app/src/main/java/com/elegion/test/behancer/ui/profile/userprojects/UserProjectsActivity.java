package com.elegion.test.behancer.ui.profile.userprojects;

import android.support.v4.app.Fragment;

import com.elegion.test.behancer.common.SingleFragmentActivity;
import com.elegion.test.behancer.ui.projects.ProjectsFragment;

/**
 * Created by Vladislav Falzan.
 */

public class UserProjectsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return UserProjectsFragment.newInstance();
    }
}

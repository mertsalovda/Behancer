package com.elegion.test.behancer.ui.userprojects;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.ui.BaseProjectsViewModel;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;

public class UserProjectsViewModel extends BaseProjectsViewModel {

    private String mUserName;

    public UserProjectsViewModel(Storage storage, ProjectsAdapter.OnItemClickListener onItemClickListener, String name) {
        super(storage, onItemClickListener);
        mUserName = name;
        mProjects = storage.getUserProjectsPaged(mUserName);
        updateProjects();
    }
}

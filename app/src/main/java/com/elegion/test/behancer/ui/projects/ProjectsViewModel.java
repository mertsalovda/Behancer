package com.elegion.test.behancer.ui.projects;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.ui.BaseProjectsViewModel;

public class ProjectsViewModel extends BaseProjectsViewModel {

    public ProjectsViewModel(Storage storage, ProjectsAdapter.OnItemClickListener onItemClickListener) {
        super(storage, onItemClickListener);
        mProjects = mStorage.getProjectsPaged();
        updateProjects();
    }
}
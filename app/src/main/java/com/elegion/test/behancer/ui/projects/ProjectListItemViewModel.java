package com.elegion.test.behancer.ui.projects;

import com.elegion.test.behancer.data.model.project.RichProject;
import com.elegion.test.behancer.utils.DateUtils;

public class ProjectListItemViewModel {

    private static final int FIRST_OWNER_INDEX = 0;

    private String mImageUrl;
    private String mName;
    private String mUsername;
    private String mPublishedOn;

    public ProjectListItemViewModel(RichProject project) {
        mImageUrl = project.mProject.getCover().getPhotoUrl();
        mName = project.mProject.getName();
        mPublishedOn = DateUtils.format(project.mProject.getPublishedOn());
        if (project.mOwners != null && project.mOwners.size() != 0) {
            mUsername = project.mOwners.get(FIRST_OWNER_INDEX).getUsername();
        }
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getName() {
        return mName;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getPublishedOn() {
        return mPublishedOn;
    }
}

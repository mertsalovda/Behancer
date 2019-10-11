package com.elegion.test.behancer.ui.profile;

import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.utils.DateUtils;

public class ProfileUserViewModel {

    private String mImageUrl;
    private String mProfileName;
    private String mProfileDisplayName;
    private String mProfileCreatedOn;
    private String mProfileLocation;

    public ProfileUserViewModel(User user) {
        mImageUrl = user.getImage().getPhotoUrl();
        mProfileName = user.getUsername();
        mProfileDisplayName = user.getDisplayName();
        mProfileCreatedOn = DateUtils.format(user.getCreatedOn());
        mProfileLocation = user.getLocation();
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getProfileName() {
        return mProfileName;
    }

    public String getProfileCreatedOn() {
        return mProfileCreatedOn;
    }

    public String getProfileLocation() {
        return mProfileLocation;
    }

    public String getProfileDisplayName() {
        return mProfileDisplayName;
    }
}



package com.elegion.test.behancer.data.model.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Vladislav Falzan.
 */

public class UserResponse implements Serializable {

    @SerializedName("user")
    private User mUser;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}

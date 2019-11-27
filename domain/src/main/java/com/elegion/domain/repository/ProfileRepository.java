package com.elegion.domain.repository;

import com.elegion.domain.model.user.User;
import com.elegion.domain.model.user.UserResponse;

import io.reactivex.Single;

public interface ProfileRepository {
    public static final String SERVER = "SERVER";
    public static final String DB = "DB";

    Single<User> getUser(String username);

    void insertUser(UserResponse response);
}

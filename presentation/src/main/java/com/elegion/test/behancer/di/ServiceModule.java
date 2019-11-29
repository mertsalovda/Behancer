package com.elegion.test.behancer.di;

import com.elegion.domain.service.ProfileService;
import com.elegion.domain.service.ProfileServiceImpl;
import com.elegion.domain.service.ProjectService;
import com.elegion.domain.service.ProjectServiceImpl;

import toothpick.config.Module;

public class ServiceModule extends Module {

    public ServiceModule() {
        bind(ProjectService.class).to(ProjectServiceImpl.class);
        bind(ProfileService.class).to(ProfileServiceImpl.class);
    }
}

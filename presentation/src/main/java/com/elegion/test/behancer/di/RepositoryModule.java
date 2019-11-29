package com.elegion.test.behancer.di;

import com.elegion.data.repository.ProfileDBRepository;
import com.elegion.data.repository.ProfileServerRepository;
import com.elegion.data.repository.ProjectDBRepository;
import com.elegion.data.repository.ProjectServerRepository;
import com.elegion.domain.repository.ProfileRepository;
import com.elegion.domain.repository.ProjectRepository;

import toothpick.config.Module;

public class RepositoryModule extends Module {

    public RepositoryModule() {
        bind(ProjectRepository.class).withName(ProjectRepository.SERVER).to(ProjectServerRepository.class);
        bind(ProjectRepository.class).withName(ProjectRepository.DB).to(ProjectDBRepository.class);
        bind(ProfileRepository.class).withName(ProfileRepository.SERVER).to(ProfileServerRepository.class);
        bind(ProfileRepository.class).withName(ProfileRepository.DB).to(ProfileDBRepository.class);
    }
}

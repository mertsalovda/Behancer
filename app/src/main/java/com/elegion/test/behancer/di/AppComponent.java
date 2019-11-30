package com.elegion.test.behancer.di;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.ui.profile.dagger.ProfileComponent;
import com.elegion.test.behancer.ui.projects.dagger.ProjectsComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tanchuev on 23.04.2018.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    Storage getStorage();
    BehanceApi getBehanceApi();

    ProfileComponent createProfileComponent();
    ProjectsComponent createProjectsComponent();
}

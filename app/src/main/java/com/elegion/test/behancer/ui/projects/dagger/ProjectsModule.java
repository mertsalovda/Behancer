package com.elegion.test.behancer.ui.projects.dagger;

import com.elegion.test.behancer.di.PerFragment;
import com.elegion.test.behancer.ui.profile.ProfilePresenter;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.projects.ProjectsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ProjectsModule {

    @Provides
    @PerFragment
    ProjectsPresenter provideProjectsPresenter(){
        return new ProjectsPresenter();
    }

    @Provides
    @PerFragment
    ProjectsAdapter provideProjectsAdapter(){
        return new ProjectsAdapter();
    }
}

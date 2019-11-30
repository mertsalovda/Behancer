package com.elegion.test.behancer.di;

import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.ui.profile.ProfileView;
import com.elegion.test.behancer.ui.projects.ProjectsView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    private final BaseView mBaseView;

    public ViewModule(BaseView baseView) {
        mBaseView = baseView;
    }

    @PerFragment
    @Provides
    ProfileView provideProfileView(){
        return (ProfileView)mBaseView;
    }

    @PerFragment
    @Provides
    ProjectsView provideProjectsView(){
        return (ProjectsView)mBaseView;
    }
}

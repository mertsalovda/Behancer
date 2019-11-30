package com.elegion.test.behancer.di;

import com.elegion.test.behancer.ui.profile.ProfilePresenter;
import com.elegion.test.behancer.ui.projects.ProjectsPresenter;

import dagger.Component;

@PerFragment
@Component(dependencies = AppComponent.class, modules = {ViewModule.class})
public interface ViewComponent {
    void inject(ProfilePresenter injectror);
    void inject(ProjectsPresenter injectror);
}

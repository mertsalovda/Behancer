package com.elegion.test.behancer.ui.projects.dagger;

import com.elegion.test.behancer.di.PerFragment;
import com.elegion.test.behancer.ui.projects.ProjectsFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = ProjectsModule.class)
public interface ProjectsComponent {
    void inject(ProjectsFragment injector);
}

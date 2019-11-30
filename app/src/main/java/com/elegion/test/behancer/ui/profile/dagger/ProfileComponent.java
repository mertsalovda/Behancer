package com.elegion.test.behancer.ui.profile.dagger;

import com.elegion.test.behancer.di.PerFragment;
import com.elegion.test.behancer.ui.profile.ProfileFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = ProfileModule.class)
public interface ProfileComponent {
    void inject(ProfileFragment injector);
}

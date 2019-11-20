package com.elegion.test.behancer.ui.profile.dagger;

import com.elegion.test.behancer.di.PerFragment;
import com.elegion.test.behancer.ui.profile.ProfilePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileModule {

    @Provides
    @PerFragment
    ProfilePresenter provideProfilePresenter(){
        return new ProfilePresenter();
    }
}

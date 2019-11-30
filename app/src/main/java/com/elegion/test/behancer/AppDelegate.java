package com.elegion.test.behancer;

import android.app.Application;

import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.di.AppComponent;
import com.elegion.test.behancer.di.AppModule;
import com.elegion.test.behancer.di.DaggerAppComponent;
import com.elegion.test.behancer.di.NetworkModule;
import com.elegion.test.behancer.di.ViewModule;
import com.elegion.test.behancer.ui.profile.dagger.ProfileComponent;
import com.elegion.test.behancer.ui.projects.dagger.ProjectsComponent;

/**
 * Created by Vladislav Falzan.
 */

public class AppDelegate extends Application {

    private static AppComponent sAppComponent;
    private static ProfileComponent sProfileComponent;
    private static ProjectsComponent sProjectsComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public static ProfileComponent getProfileComponent(){
        if (sProfileComponent == null){
            sProfileComponent = sAppComponent.createProfileComponent();
        }
        return sProfileComponent;
    }

    public static void clearProfileComponent(){
        sProfileComponent = null;
    }

    public static ProjectsComponent getProjectsComponent(){
        if (sProjectsComponent == null){
            sProjectsComponent = sAppComponent.createProjectsComponent();
        }
        return sProjectsComponent;
    }

    public static void clearProjectsComponent(){
        sProjectsComponent = null;
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}

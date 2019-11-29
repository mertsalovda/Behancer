package com.elegion.test.behancer;

import android.app.Application;

import com.elegion.test.behancer.di.AppModule;
import com.elegion.test.behancer.di.NetworkModule;
import com.elegion.test.behancer.di.RepositoryModule;
import com.elegion.test.behancer.di.ServiceModule;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.smoothie.module.SmoothieApplicationModule;

/**
 * Created by Vladislav Falzan.
 */

public class AppDelegate extends Application {

    public static Scope sAppScope;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppScope = Toothpick.openScope(AppDelegate.class);
        sAppScope.installModules(new SmoothieApplicationModule(this), new AppModule(this),
                new NetworkModule(), new RepositoryModule(), new ServiceModule());
    }

    public static Scope getAppScope() {
        return sAppScope;
    }
}

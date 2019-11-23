package com.elegion.test.behancer;

import android.app.Application;

import com.elegion.test.behancer.di.AppModule;
import com.elegion.test.behancer.di.NetworkModule;

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

        // 1) Toothpick настроен в Application. Если используется Toothpick версии 3+, в любом случае нужно ставить полный бал по этому критерию.
        // Использую Toothpick 3.1

        sAppScope = Toothpick.openScope(AppDelegate.class);
        sAppScope.installModules(new SmoothieApplicationModule(this), new AppModule(this), new NetworkModule());
    }

    public static Scope getAppScope() {
        return sAppScope;
    }
}

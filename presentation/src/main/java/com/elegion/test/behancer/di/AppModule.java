package com.elegion.test.behancer.di;

import android.arch.persistence.room.Room;

import com.elegion.data.database.BehanceDao;
import com.elegion.data.database.BehanceDatabase;
import com.elegion.test.behancer.AppDelegate;

import toothpick.config.Module;

public class AppModule extends Module {

    private final AppDelegate mApp;
    private final BehanceDatabase mBehanceDatabase;

    public AppModule(AppDelegate mApp) {
        this.mApp = mApp;
        mBehanceDatabase = provideDatabase();
        bind(AppDelegate.class).toInstance(provideApp());
        bind(BehanceDatabase.class).toInstance(mBehanceDatabase);
        bind(BehanceDao.class).toInstance(provideBehanceDao());
    }

    AppDelegate provideApp() {
        return mApp;
    }

    BehanceDatabase provideDatabase() {
        return Room.databaseBuilder(mApp, BehanceDatabase.class, "behance_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    BehanceDao provideBehanceDao() {
        return mBehanceDatabase.getBehanceDao();
    }
}
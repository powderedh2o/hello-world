package com.pw.helloworld.application;

import android.app.Application;

import timber.log.Timber;

public class HelloWorldApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}

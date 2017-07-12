package com.pw.helloworld.application;

import com.pw.helloworld.destination1.Destination1Fragment;
import com.pw.helloworld.users.UserApiClient;
import com.pw.helloworld.users.UserApiClientImpl;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ApplicationModule {

    @Provides
    static UserApiClient provideUserApiClient() {
        return new UserApiClientImpl();
    }

    @ContributesAndroidInjector
    abstract Destination1Fragment contributeDestination1FragmentInjector();
}

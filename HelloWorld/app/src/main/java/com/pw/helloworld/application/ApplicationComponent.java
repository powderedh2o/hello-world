package com.pw.helloworld.application;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {AndroidInjectionModule.class, ApplicationModule.class})
public interface ApplicationComponent extends AndroidInjector<HelloWorldApplication> {
}

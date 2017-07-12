package com.pw.helloworld;

import com.pw.helloworld.dagger.YourActivityModule;

import dagger.Component;
import dagger.android.AndroidInjector;

@Component(modules = {YourActivityModule.class})
interface YourApplicationComponent extends AndroidInjector<HelloWorldApplication> {
}

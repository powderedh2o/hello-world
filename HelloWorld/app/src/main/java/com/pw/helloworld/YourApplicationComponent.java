package com.pw.helloworld;

import com.pw.helloworld.dagger.YourActivityModule;
import com.pw.helloworld.dagger.YourFragmentModule;

import dagger.Component;
import dagger.android.AndroidInjector;

@Component(modules = {YourActivityModule.class, YourFragmentModule.class})
interface YourApplicationComponent extends AndroidInjector<HelloWorldApplication> {
}

package com.pw.helloworld;

import com.pw.helloworld.dagger.YourActivityModule;
import com.pw.helloworld.dagger.YourFragmentModule;
import com.pw.helloworld.dagger.YourGenericModule;

import dagger.Component;
import dagger.android.AndroidInjector;

@Component(modules = {YourGenericModule.class, YourActivityModule.class, YourFragmentModule.class})
interface YourApplicationComponent extends AndroidInjector<HelloWorldApplication> {
}

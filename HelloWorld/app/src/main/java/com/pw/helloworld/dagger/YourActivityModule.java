package com.pw.helloworld.dagger;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = YourActivitySubcomponent.class)
public abstract class YourActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(YourActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindYourActivityInjectorFactory(YourActivitySubcomponent.Builder builder);
}

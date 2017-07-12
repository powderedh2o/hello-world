package com.pw.helloworld.dagger;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = YourFragmentSubcomponent.class)
public abstract class YourFragmentModule {

    @Binds
    @IntoMap
    @dagger.android.support.FragmentKey(YourFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    bindYourFragmentInjectorFactory(YourFragmentSubcomponent.Builder builder);
}

package com.pw.helloworld.dagger;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent()
public interface YourActivitySubcomponent extends AndroidInjector<YourActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<YourActivity> {
    }
}

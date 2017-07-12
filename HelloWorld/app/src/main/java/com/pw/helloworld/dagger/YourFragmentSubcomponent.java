package com.pw.helloworld.dagger;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent()
public interface YourFragmentSubcomponent extends AndroidInjector<YourFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<YourFragment> {
    }
}

package com.pw.helloworld.dagger;

import dagger.Module;
import dagger.Provides;

@Module
public class YourGenericModule {

    @Provides
    static MessageGenerator provideMessageGenerator() {
        return new FancyMessageGenerator();
    }
}

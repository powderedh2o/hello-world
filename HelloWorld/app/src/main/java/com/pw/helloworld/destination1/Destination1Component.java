package com.pw.helloworld.destination1;

import com.pw.restclient.RestClientModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {RestClientModule.class, Destination1Module.class})
@Singleton
public interface Destination1Component {

    void inject(Destination1Fragment destination1Fragment);
}

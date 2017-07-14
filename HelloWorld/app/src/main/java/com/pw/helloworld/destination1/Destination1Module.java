package com.pw.helloworld.destination1;

import android.arch.lifecycle.Lifecycle;

import com.pw.restclient.RestApiMediator;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

@Module
public final class Destination1Module {

    private final Destination1Contract.View view;
    private final LifecycleProvider<Lifecycle.Event> lifecycleProvider;

    public Destination1Module(Destination1Contract.View view, LifecycleProvider<Lifecycle.Event> lifecycleProvider) {
        this.view = view;
        this.lifecycleProvider = lifecycleProvider;
    }

    @Provides
    public Destination1Contract.Model provideDestination1Model(RestApiMediator restApiMediator) {
        return new Destination1Model(restApiMediator);
    }

    @Provides
    public Destination1Contract.Presenter provideDestination1Presenter(Destination1Contract.Model model) {
        return new Destination1Presenter(model, view, lifecycleProvider);
    }
}

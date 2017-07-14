package com.pw.helloworld.destination1;

import android.arch.lifecycle.Lifecycle;

import com.pw.restclient.AugmentedUser;
import com.pw.restclient.RestApiMediator;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class Destination1Model implements Destination1Contract.Model {

    private final RestApiMediator restApiMediator;

    @Inject
    public Destination1Model(RestApiMediator restApiMediator) {
        this.restApiMediator = restApiMediator;
    }

    @Override
    public void loadUsers(Consumer<? super List<AugmentedUser>> onNext, Consumer<? super Throwable> onError, LifecycleProvider<Lifecycle.Event> lifecycleProvider) {
        restApiMediator.loadUsers(onNext, onError, lifecycleProvider);
    }
}

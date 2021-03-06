package com.pw.restclient;

import android.arch.lifecycle.Lifecycle;

import com.pw.restclient.AugmentedUser;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.functions.Consumer;

public interface RestApiMediator {

    void loadUsers(Consumer<? super List<AugmentedUser>> onNext, Consumer<? super Throwable> onError, LifecycleProvider<Lifecycle.Event> lifecycleProvider);
}

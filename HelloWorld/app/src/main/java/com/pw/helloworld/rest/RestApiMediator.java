package com.pw.helloworld.rest;

import java.util.List;

import io.reactivex.functions.Consumer;

public interface RestApiMediator {

    void loadUsers(Consumer<? super List<AugmentedUser>> onNext, Consumer<? super Throwable> onError);

    void dispose();
}

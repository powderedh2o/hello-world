package com.pw.restclient;

import android.arch.lifecycle.Lifecycle;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RestApiMediatorImpl implements RestApiMediator {

    private final RestApiClient restApiClient;

    public RestApiMediatorImpl(RestApiClient restApiClient) {
        this.restApiClient = restApiClient;
    }

    @Override
    public void loadUsers(Consumer<? super List<AugmentedUser>> onLoadSucceeded, Consumer<? super Throwable> onError, LifecycleProvider<Lifecycle.Event> lifecycleProvider) {
        Observable.zip(
                restApiClient.loadUsers(),
                restApiClient.loadPosts(),
                this::getAugmentedUsers
        ).subscribeOn(
                Schedulers.io()
        ).observeOn(
                AndroidSchedulers.mainThread()
        ).compose(
                lifecycleProvider.bindToLifecycle()
        ).subscribe(
                onLoadSucceeded::accept,
                onError::accept
        );
    }

    private List<AugmentedUser> getAugmentedUsers(List<User> users, List<Post> posts) {
        List<AugmentedUser> augmentedUsers = new ArrayList<>(users.size());

        for (User user : users) {

            int userId = user.getId();
            int postCount = 0;
            for (Post post : posts) {
                if (userId == post.getUserId()) {
                    postCount++;
                }
            }

            augmentedUsers.add(new AugmentedUser(user.getUsername(), user.getName(), postCount));
        }

        return augmentedUsers;
    }
}

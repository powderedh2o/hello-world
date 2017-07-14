package com.pw.helloworld.destination1;

import android.arch.lifecycle.Lifecycle;

import com.pw.restclient.AugmentedUser;
import com.trello.rxlifecycle2.LifecycleProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import timber.log.Timber;

public class Destination1Presenter implements Destination1Contract.Presenter {

    private final Destination1Contract.Model model;
    private final Destination1Contract.View view;
    private final LifecycleProvider<Lifecycle.Event> lifecycleProvider;

    public Destination1Presenter(@NotNull Destination1Contract.Model model, @NotNull Destination1Contract.View view, @NotNull LifecycleProvider<Lifecycle.Event> lifecycleProvider) {
        this.model = model;
        this.view = view;
        this.lifecycleProvider = lifecycleProvider;

        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        Timber.d("Starting presenter");
        loadUsers();
    }

    @Override
    public void refresh() {
        Timber.d("Refreshing users");
        view.hideRefreshing();
        loadUsers();
    }

    private void loadUsers() {
        Timber.d("Loading users");
        model.loadUsers(this::onLoadUsersSucceeded, this::onLoadUsersFailed, lifecycleProvider);
    }

    private void onLoadUsersSucceeded(List<AugmentedUser> users) {
        Timber.d("Successfully loaded users");
        view.displayUsers(users);
    }

    private void onLoadUsersFailed(Throwable t) {
        Timber.d(t, "Failed to load users");
    }
}

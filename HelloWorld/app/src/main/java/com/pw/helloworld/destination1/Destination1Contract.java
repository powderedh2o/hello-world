package com.pw.helloworld.destination1;

import android.arch.lifecycle.Lifecycle;

import com.pw.helloworld.BasePresenter;
import com.pw.helloworld.BaseView;
import com.pw.helloworld.rest.AugmentedUser;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.functions.Consumer;

public interface Destination1Contract {

    interface Model {

        void loadUsers(Consumer<? super List<AugmentedUser>> onNext, Consumer<? super Throwable> onError, LifecycleProvider<Lifecycle.Event> lifecycleProvider);
    }

    interface View extends BaseView<Presenter> {

        void hideRefreshing();

        void displayUsers(List<AugmentedUser> users);
    }

    interface Presenter extends BasePresenter {

        void refresh();
    }
}

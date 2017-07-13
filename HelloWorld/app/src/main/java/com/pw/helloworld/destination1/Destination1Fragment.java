package com.pw.helloworld.destination1;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pw.helloworld.R;
import com.pw.helloworld.rest.AugmentedUser;
import com.pw.helloworld.rest.RestApiMediator;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;

public class Destination1Fragment extends LifecycleFragment {

    private final LifecycleProvider<Lifecycle.Event> lifecycleProvider = AndroidLifecycle.createLifecycleProvider(this);

    @BindView(R.id.destination_1_swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.destination_1_recycler_view)
    RecyclerView recyclerView;

    @Inject
    RestApiMediator restApiMediator;

    @Inject
    Destination1Adapter adapter;

    private Unbinder unbinder;

    private final SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            Timber.d("Refreshing Users");
            swipeRefresh.setRefreshing(false);
            loadUsers();
        }
    };

    public static Destination1Fragment newInstance() {
        return new Destination1Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_destination1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefresh.setOnRefreshListener(onRefreshListener);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();

        loadUsers();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    private void loadUsers() {
        Timber.d("Loading users");
        restApiMediator.loadUsers(
                this::onLoadSucceeded,
                this::onLoadFailed,
                lifecycleProvider);
    }

    private void onLoadSucceeded(List<AugmentedUser> augmentedUsers) {
        Timber.d("Successfully loaded data");
        adapter.setUsers(augmentedUsers);
    }

    private void onLoadFailed(Throwable t) {
        Timber.d(t, "Failed to load data");
    }
}

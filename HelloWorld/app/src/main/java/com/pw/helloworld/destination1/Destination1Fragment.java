package com.pw.helloworld.destination1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pw.helloworld.BaseFragment;
import com.pw.helloworld.R;
import com.pw.restclient.AugmentedUser;
import com.pw.restclient.RestClientModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class Destination1Fragment extends BaseFragment implements Destination1Contract.View {

    @BindView(R.id.destination_1_swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.destination_1_recycler_view)
    RecyclerView recyclerView;

    @Inject
    Destination1Contract.Model model;

    @Inject
    Destination1Contract.Presenter presenter;

    @Inject
    Destination1Adapter adapter;

    public static Destination1Fragment newInstance() {
        return new Destination1Fragment();
    }

    @Override
    public void setPresenter(@NonNull Destination1Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerDestination1Component
                .builder()
                .restClientModule(new RestClientModule(getContext().getApplicationContext()))
                .destination1Module(new Destination1Module(this, getLifecycleProvider()))
                .build()
                .inject(this);
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_destination1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefresh.setOnRefreshListener(() -> presenter.refresh());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.start();
    }

    @Override
    public void hideRefreshing() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void displayUsers(List<AugmentedUser> users) {
        adapter.setUsers(users);
    }
}

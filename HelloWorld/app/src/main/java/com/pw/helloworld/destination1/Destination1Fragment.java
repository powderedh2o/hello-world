package com.pw.helloworld.destination1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pw.helloworld.R;
import com.pw.helloworld.users.User;
import com.pw.helloworld.users.UserApiClient;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class Destination1Fragment extends Fragment {

    @BindString(R.string.base_url)
    String baseUrl;

    @BindView(R.id.destination_1_swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.destination_1_recycler_view)
    RecyclerView recyclerView;

    private Destination1Adapter adapter;
    private UserApiClient userApiClient;
    private Unbinder unbinder;

    private final SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            Timber.d("Refreshing Users");
            swipeRefresh.setRefreshing(false);
            loadUsers();
        }
    };

    private final UserApiClient.OnLoadListener onLoadListener = new UserApiClient.OnLoadListener() {

        @Override
        public void onUsersLoaded(List<User> users) {
            Timber.d("Users loaded");
            adapter.setUsers(users);
        }
    };

    public static Destination1Fragment newInstance() {
        return new Destination1Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_destination1, container, false);
        unbinder = ButterKnife.bind(this, view);
        userApiClient = new UserApiClient.Builder().baseUrl(baseUrl).build();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefresh.setOnRefreshListener(onRefreshListener);

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new Destination1Adapter();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        loadUsers();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void loadUsers() {
        Timber.d("Loading users");
        userApiClient.loadUsers(onLoadListener);
    }
}

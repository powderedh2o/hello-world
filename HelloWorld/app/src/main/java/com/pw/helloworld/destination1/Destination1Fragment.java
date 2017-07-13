package com.pw.helloworld.destination1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pw.helloworld.R;
import com.pw.helloworld.rest.AugmentedUser;
import com.pw.helloworld.rest.Post;
import com.pw.helloworld.rest.RestApiClient;
import com.pw.helloworld.rest.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class Destination1Fragment extends Fragment {

    @BindView(R.id.destination_1_swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.destination_1_recycler_view)
    RecyclerView recyclerView;

    @Inject
    RestApiClient restApiClient;

    @Inject
    Destination1Adapter adapter;

    private Unbinder unbinder;
    private Disposable disposable;

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

        loadUsers();
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    @Override
    public void onStop() {
        super.onStop();

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    private void loadUsers() {
        Timber.d("Loading users");

        Observable<List<User>> usersObservable =
                restApiClient.loadUsers()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

        Observable<List<Post>> postsObservable =
                restApiClient.loadPosts()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

        disposable = Observable.zip(
                usersObservable,
                postsObservable,
                (users, posts) -> Pair.create(users, posts)
        ).subscribe(
                usersAndPosts -> onLoadSucceeded(usersAndPosts),
                throwable -> onLoadFailed(throwable)
        );
    }

    private void onLoadSucceeded(Pair<List<User>, List<Post>> usersAndPosts) {
        Timber.d("Successfully loaded data");
        adapter.setUsers(getAugmentedUsers(usersAndPosts));
    }

    private List<AugmentedUser> getAugmentedUsers(Pair<List<User>, List<Post>> usersAndPosts) {
        List<AugmentedUser> augmentedUsers = new ArrayList<>(usersAndPosts.first.size());

        for (User user : usersAndPosts.first) {

            int userId = user.getId();
            int posts = 0;
            for (Post post : usersAndPosts.second) {
                if (userId == post.getUserId()) {
                    posts++;
                }
            }

            augmentedUsers.add(new AugmentedUser(user.getUsername(), user.getName(), posts));
        }

        return augmentedUsers;
    }

    private void onLoadFailed(Throwable t) {
        Timber.d(t, "Failed to load data");
    }
}

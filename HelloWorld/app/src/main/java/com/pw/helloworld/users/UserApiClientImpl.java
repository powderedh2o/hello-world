package com.pw.helloworld.users;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class UserApiClientImpl implements UserApiClient {

    private final UserApi userApi;

    public UserApiClientImpl(UserApi userApi) {
        this.userApi = userApi;
    }

    @Override
    public void loadUsers(final OnLoadListener listener) {
        Timber.d("Loading users");
        userApi.listUsers().enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if (response.isSuccessful()) {
                    Timber.d("Successfully loaded users");
                    listener.onUsersLoaded(response.body());
                } else {
                    Timber.e("Failed to load users");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                Timber.e(t, "Failed to load users");
            }
        });
    }
}

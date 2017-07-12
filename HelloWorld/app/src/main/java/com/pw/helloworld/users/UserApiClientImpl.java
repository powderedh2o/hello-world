package com.pw.helloworld.users;

import android.support.annotation.NonNull;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class UserApiClientImpl implements UserApiClient {

    private UserApi userApi;

    @Override
    public void init(String baseUrl) {
        if (baseUrl == null) {
            throw new IllegalStateException("baseUrl is null");
        }

        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor).build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userApi = retrofit.create(UserApi.class);
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

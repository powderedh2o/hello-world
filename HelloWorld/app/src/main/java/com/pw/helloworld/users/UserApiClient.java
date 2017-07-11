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

public class UserApiClient {

    private final UserApi userApi;

    private UserApiClient(UserApi userApi) {
        this.userApi = userApi;
    }

    public void loadUsers(final OnLoadListener listener) {
        Timber.d("Loading users");
        userApi.listUsers().enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                Timber.d("Successfully loaded users");
                listener.onUsersLoaded(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                Timber.e(t, "Failed to load users");
            }
        });
    }

    public static final class Builder {

        private String baseUrl;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public UserApiClient build() {
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

            final UserApi userApi = retrofit.create(UserApi.class);

            return new UserApiClient(userApi);
        }
    }

    public interface OnLoadListener {
        void onUsersLoaded(List<User> users);
    }
}

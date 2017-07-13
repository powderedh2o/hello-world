package com.pw.helloworld.users;

import java.util.List;

import io.reactivex.Observable;

public class UserApiClientImpl implements UserApiClient {

    private final UserApi userApi;

    public UserApiClientImpl(UserApi userApi) {
        this.userApi = userApi;
    }

    @Override
    public Observable<List<User>> loadUsers() {
        return userApi.listUsers();
    }
}

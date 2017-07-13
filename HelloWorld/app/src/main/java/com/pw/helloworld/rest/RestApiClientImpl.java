package com.pw.helloworld.rest;

import java.util.List;

import io.reactivex.Observable;

public class RestApiClientImpl implements RestApiClient {

    private final RestApi restApi;

    public RestApiClientImpl(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<List<User>> loadUsers() {
        return restApi.listUsers();
    }

    @Override
    public Observable<List<Post>> loadPosts() {
        return restApi.listPosts();
    }
}

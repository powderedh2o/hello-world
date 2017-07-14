package com.pw.restclient;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RestApi {

    @GET("users")
    Observable<List<User>> listUsers();

    @GET("posts")
    Observable<List<Post>> listPosts();
}

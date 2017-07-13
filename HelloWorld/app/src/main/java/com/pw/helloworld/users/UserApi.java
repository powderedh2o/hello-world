package com.pw.helloworld.users;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UserApi {

    @GET("users")
    Observable<List<User>> listUsers();
}

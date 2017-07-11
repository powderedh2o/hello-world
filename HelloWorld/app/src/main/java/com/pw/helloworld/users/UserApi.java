package com.pw.helloworld.users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface UserApi {

    @GET("users")
    Call<List<User>> listUsers();
}

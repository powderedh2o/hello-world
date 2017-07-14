package com.pw.helloworld.rest;

import com.pw.restclient.Post;
import com.pw.restclient.User;

import java.util.List;

import io.reactivex.Observable;

public interface RestApiClient {

    Observable<List<User>> loadUsers();

    Observable<List<Post>> loadPosts();
}

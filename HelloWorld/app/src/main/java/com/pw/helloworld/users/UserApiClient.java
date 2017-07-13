package com.pw.helloworld.users;

import java.util.List;

import io.reactivex.Observable;

public interface UserApiClient {

    Observable<List<User>> loadUsers();
}

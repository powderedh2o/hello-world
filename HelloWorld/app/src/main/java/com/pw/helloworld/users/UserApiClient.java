package com.pw.helloworld.users;

import java.util.List;

public interface UserApiClient {

    void init(String baseUrl);

    void loadUsers(OnLoadListener listener);

    interface OnLoadListener {
        void onUsersLoaded(List<User> users);
    }
}

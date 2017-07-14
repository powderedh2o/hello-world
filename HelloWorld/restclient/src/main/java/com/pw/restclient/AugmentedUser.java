package com.pw.restclient;

public class AugmentedUser {

    private final String username;
    private final String name;
    private final int posts;

    public AugmentedUser(String username, String name, int posts) {
        this.username = username;
        this.name = name;
        this.posts = posts;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public int getPosts() {
        return posts;
    }
}

package com.example.messagingstompwebsocket;

import java.security.Principal;

@SuppressWarnings("ClassCanBeRecord")
public class User implements Principal {

    private final String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}

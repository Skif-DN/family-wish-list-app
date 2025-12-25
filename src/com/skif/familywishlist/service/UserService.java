package com.skif.familywishlist.service;

import com.skif.familywishlist.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
    }

    public User createUser(String username, String password) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("Username and password cannot be null");
        }

        if (getUserByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User(username, password);
        users.add(user);
        return user;
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void deleteUser(User user) {
        if (user != null) {
            users.remove(user);
        }
    }

    public boolean usernameExists(String username) {
        return getUserByUsername(username) != null;
    }
}

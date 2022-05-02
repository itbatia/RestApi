package com.itbatia.app.controller;

import com.itbatia.app.model.*;
import com.itbatia.app.service.UserService;

import java.util.List;

public class UserController {
    private final UserService userService = new UserService();

    public User createUser(String name, List<Event> events) {
        return userService.createUser(name, events);
    }

    public User getUser(Integer id) {
        return userService.getUser(id);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    public void deleteUser(Integer id) {
        userService.deleteUser(id);
    }
}

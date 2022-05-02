package com.itbatia.app.service;

import com.itbatia.app.model.*;
import com.itbatia.app.repositopy.UserRepository;
import com.itbatia.app.repositopy.db.DbUserRepositoryImpl;

import java.util.List;

public class UserService {
    private final UserRepository userRepository = new DbUserRepositoryImpl();

    public User createUser(String name, List<Event> events) {
        User user = new User(null, name, events);
        return userRepository.save(user);
    }

    public User getUser(Integer id) {
        return userRepository.getById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    public User updateUser(User user) {
        return userRepository.update(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}

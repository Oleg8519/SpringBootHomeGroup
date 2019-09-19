package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    Optional<User> getUserById(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
    List<User> findAll();
}

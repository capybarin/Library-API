package com.example.Library.service;

import com.example.Library.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User getById(Integer id);
    User addUser(User newUser);
}

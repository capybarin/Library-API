package com.example.Library.controller;

import com.example.Library.entity.User;
import com.example.Library.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(path = "/users", produces = "application/json")
    public List<User> all(){
        return userService.findAll();
    }

    @GetMapping(path = "/users/{id}", produces = "application/json")
    public User one(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping(path = "/register", produces = "application/json", consumes = "application/json")
    public User newUser(@RequestBody User newUser) {
        return userService.addUser(newUser);
    }

    @GetMapping(path = "/ping", produces = "application/json")
    public String ping(){
        return "pong";
    }
}

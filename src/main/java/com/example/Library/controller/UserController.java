package com.example.Library.controller;

import com.example.Library.entity.User;
import com.example.Library.service.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @ApiOperation(value = "View a list of all registered users")
    @GetMapping(path = "/users", produces = "application/json")
    public List<User> all(){
        return userService.findAll();
    }

    @ApiOperation(value = "View a user by his/her ID", response = User.class)
    @GetMapping(path = "/users/{id}", produces = "application/json")
    public User one(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @ApiOperation(value = "Register a new user", response = User.class)
    @PostMapping(path = "/register", produces = "application/json", consumes = "application/json")
    public User newUser(@RequestBody @Valid User newUser) {
        return userService.addUser(newUser);
    }

    @ApiOperation(value = "Validate credentials")
    @GetMapping(path = "/ping", produces = "application/json")
    public String ping(){
        return "pong";
    }
}

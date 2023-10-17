package com.java.service.controllers;

import com.java.service.forms.UserForm;
import com.java.service.models.User;
import com.java.service.repositories.UsersRepository;
import com.java.service.servises.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers () {

        return userService.findAll();
    }

    @GetMapping("/users/{user_id}")
    public User getUser(@PathVariable("user_id") Long userId) {
        return userService.findOne(userId);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
        userService.signUp(userForm);
        return ResponseEntity.ok().build();
    }

}

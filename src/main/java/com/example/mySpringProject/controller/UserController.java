package com.example.mySpringProject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mySpringProject.entities.User;
import com.example.mySpringProject.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @GetMapping("/{displayName}")
    public ResponseEntity<User> getUser(@PathVariable String displayName) {
        return ResponseEntity.ok(userService.getUser(displayName));
    }

    @PutMapping("/{displayName}")
    public ResponseEntity<User> updateUser(@PathVariable String displayName, @RequestBody @Valid User updatedUser) {
        return ResponseEntity.ok(userService.updateUser(displayName, updatedUser));
    }
}


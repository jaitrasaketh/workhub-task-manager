package com.workhub.demo.controller;

import com.workhub.demo.model.User;
import com.workhub.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Register a new user
    @PostMapping("")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // ✅ Get user by email
    @GetMapping("/by-email")
    public User getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email).orElse(null);
    }

    // ✅ Get user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id).orElse(null);
    }

    // ✅ Update user name or profile picture
    @PatchMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        Optional<User> existing = userService.getUserById(id);
        if (existing.isEmpty()) return null;

        User user = existing.get();
        if (updatedUser.getName() != null) user.setName(updatedUser.getName());
        if (updatedUser.getProfilePicUrl() != null) user.setProfilePicUrl(updatedUser.getProfilePicUrl());
        return userService.saveUser(user);
    }
}

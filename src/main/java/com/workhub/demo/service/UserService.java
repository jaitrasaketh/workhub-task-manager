package com.workhub.demo.service;

import com.workhub.demo.model.User;
import com.workhub.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Fetch user by ID
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    // Fetch user by email (used in login or invite)
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Create or update a user
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}

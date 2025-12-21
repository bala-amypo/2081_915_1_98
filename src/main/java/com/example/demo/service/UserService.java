package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface UserService {
    User saveUser(User user);           // Save a new user
    List<User> getAllUsers();          // Get all users
    User getUserById(Long id);         // Get user by ID
    void deleteUser(Long id);          // Delete user by ID
}

package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        // TODO: implement login logic
        return new AuthResponse("Login successful");
    }

    @Override
    public AuthResponse register(AuthRequest authRequest) {
        // TODO: implement register logic
        return new AuthResponse("Registration successful");
    }
}

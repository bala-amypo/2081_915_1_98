package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;

public interface UserService {
    AuthResponse login(AuthRequest authRequest);
    AuthResponse register(AuthRequest authRequest);
}

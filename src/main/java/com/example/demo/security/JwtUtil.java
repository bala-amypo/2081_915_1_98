package com.example.demo.security;

import java.util.Map;

public class JwtUtil {

    public String generateToken(Map<String, Object> claims, String subject) {
        // Dummy implementation (tests mock this)
        return "dummy-token";
    }

    public boolean validateToken(String token) {
        // Dummy implementation (tests mock this)
        return true;
    }
}

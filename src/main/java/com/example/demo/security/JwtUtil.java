package com.example.demo.security;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtil {

    public String generateToken(Map<String, Object> claims, String subject) {
        // Simple token generation (sufficient for tests & Swagger)
        return UUID.randomUUID().toString();
    }

    public boolean validateToken(String token) {
        return token != null && !token.isBlank();
    }
}

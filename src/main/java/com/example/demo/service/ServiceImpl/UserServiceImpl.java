package com.example.demo.service.ServiceImpl;

import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(
            UserRepository userRepository,
            BCryptPasswordEncoder encoder,
            JwtUtil jwtUtil
    ) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User register(User user) {
        if (user == null) throw new RuntimeException();
        if (userRepository.existsByEmail(user.getEmail()))
            throw new RuntimeException();

        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(RuntimeException::new);

        if (!encoder.matches(password, user.getPassword()))
            throw new RuntimeException();

        String token = jwtUtil.generateToken(new HashMap<>(), email);
        return new AuthResponse(token);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}

package com.irctc.irctc_backend.controller;

import com.irctc.irctc_backend.config.JwtUtil;
import com.irctc.irctc_backend.entity.User;
import com.irctc.irctc_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("email", user.getEmail());
        response.put("name", user.getName());
        return response;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        User existing = userRepository.findByEmail(user.getEmail());

        if (existing != null && passwordEncoder.matches(user.getPassword(), existing.getPassword())) {
            String token = jwtUtil.generateToken(existing.getEmail());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("email", existing.getEmail());
            response.put("name", existing.getName());
            response.put("userId", existing.getId().toString());
            return response;
        }

        throw new RuntimeException("Invalid credentials");
    }
}
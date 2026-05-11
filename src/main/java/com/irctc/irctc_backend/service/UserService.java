package com.irctc.irctc_backend.service;

import com.irctc.irctc_backend.entity.User;
import com.irctc.irctc_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }
}
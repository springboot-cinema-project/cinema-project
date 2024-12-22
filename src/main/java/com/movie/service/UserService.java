package com.movie.service;

import com.movie.domain.User;
import com.movie.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(User user) {
        if (isEmailRegistered(user.getEmail())) {
            throw new IllegalArgumentException("このメールアドレスは既に登録されています: " + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insertUser(user);
    }

    public boolean isEmailRegistered(String email) {
        return userMapper.existsByEmail(email);
    }

    public Optional<User> findByEmail(String email) {
        return userMapper.findByEmail(email);
    }
}

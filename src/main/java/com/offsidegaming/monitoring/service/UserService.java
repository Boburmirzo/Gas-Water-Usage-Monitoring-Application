package com.offsidegaming.monitoring.service;

import com.offsidegaming.monitoring.model.entity.User;
import com.offsidegaming.monitoring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long userId) {
        if (Objects.nonNull(userId)) {
            return userRepository.findById(userId).orElseThrow(() -> new RuntimeException(String.format("User with give id %s not found.", userId)));
        } else {
            throw new RuntimeException("Null user id is not allowed.");
        }
    }

    public boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }
}

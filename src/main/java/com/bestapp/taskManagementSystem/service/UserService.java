package com.bestapp.taskManagementSystem.service;

import com.bestapp.taskManagementSystem.dto.account.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getByEmail(String email);
}

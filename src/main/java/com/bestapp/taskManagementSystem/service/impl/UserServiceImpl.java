package com.bestapp.taskManagementSystem.service.impl;

import com.bestapp.taskManagementSystem.dto.account.User;
import com.bestapp.taskManagementSystem.exception.notFoundException.UserNotFoundException;
import com.bestapp.taskManagementSystem.model.UserEntity;
import com.bestapp.taskManagementSystem.repository.UserRepository;
import com.bestapp.taskManagementSystem.service.UserMapper;
import com.bestapp.taskManagementSystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(super.toString()));
        return Optional.of(userMapper.toUser(userEntity));
    }
}

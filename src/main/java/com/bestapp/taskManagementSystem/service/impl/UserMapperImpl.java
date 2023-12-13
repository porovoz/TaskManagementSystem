package com.bestapp.taskManagementSystem.service.impl;

import com.bestapp.taskManagementSystem.dto.account.User;
import com.bestapp.taskManagementSystem.model.UserEntity;
import com.bestapp.taskManagementSystem.service.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserMapperImpl implements UserMapper {

    // I wanted to keep password in database in encrypted form, but not finished yet.
    private final PasswordEncoder passwordEncoder;

    public UserMapperImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity toUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
//        userEntity.setId(user.getId()); // I think there is no need in this.
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setRole(user.getRoles().stream().findFirst().get()); // not sure if it's right
        return userEntity;
    }

    @Override
    public User toUser(UserEntity userEntity) {
        User user = new User();
//        user.setId(userEntity.getId()); // I think there is no need in this.
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword()); //not sure maybe like this user.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setRoles(Set.of(userEntity.getRole()));
        return user;
    }

}

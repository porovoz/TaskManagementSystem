package com.bestapp.taskManagementSystem.service;

import com.bestapp.taskManagementSystem.dto.account.User;
import com.bestapp.taskManagementSystem.model.UserEntity;

public interface UserMapper {
    UserEntity toUserEntity(User user);
    User toUser(UserEntity userEntity);
}

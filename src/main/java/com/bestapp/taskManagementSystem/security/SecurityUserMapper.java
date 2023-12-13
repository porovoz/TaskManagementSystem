package com.bestapp.taskManagementSystem.security;

import com.bestapp.taskManagementSystem.model.UserEntity;

public interface SecurityUserMapper {
    SecurityUserDto toSecurityUserDto(UserEntity userEntity);
}

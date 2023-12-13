package com.bestapp.taskManagementSystem.security.impl;

import com.bestapp.taskManagementSystem.model.UserEntity;
import com.bestapp.taskManagementSystem.security.SecurityUserDto;
import com.bestapp.taskManagementSystem.security.SecurityUserMapper;
import org.springframework.stereotype.Component;

@Component
public class SecurityUserMapperImpl implements SecurityUserMapper {
    @Override
    public SecurityUserDto toSecurityUserDto(UserEntity userEntity) {
        SecurityUserDto securityUserDto = new SecurityUserDto();
        securityUserDto.setId(userEntity.getId());
        securityUserDto.setUserName(userEntity.getEmail());
        securityUserDto.setPassword(userEntity.getPassword());
        securityUserDto.setRole(userEntity.getRole());
        return securityUserDto;
    }
}

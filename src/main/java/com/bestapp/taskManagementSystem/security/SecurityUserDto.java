package com.bestapp.taskManagementSystem.security;

import com.bestapp.taskManagementSystem.dto.account.Role;
import lombok.Data;

@Data
public class SecurityUserDto {
    private int id;
    private String userName;
    private String password;
    private Role role;
}

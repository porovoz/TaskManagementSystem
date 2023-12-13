package com.bestapp.taskManagementSystem.dto.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    USER("USER");

    private final String roleValue;

    @Override
    public String getAuthority() {
        return roleValue;
    }
}

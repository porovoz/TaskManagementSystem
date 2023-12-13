package com.bestapp.taskManagementSystem.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JasonWebTokenResponse {
    private final String tokenType = "Bearer";
    private String actualToken;
    private String renewToken;
}

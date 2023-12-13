package com.bestapp.taskManagementSystem.service;

import com.bestapp.taskManagementSystem.dto.account.JasonWebTokenRequest;
import com.bestapp.taskManagementSystem.dto.account.JasonWebTokenResponse;
import com.bestapp.taskManagementSystem.security.JasonWebTokenAuthentication;
import lombok.NonNull;

public interface AuthService {
    JasonWebTokenResponse login(@NonNull JasonWebTokenRequest authenticationRequest);

    JasonWebTokenResponse getActualToken(@NonNull String renewToken);

    JasonWebTokenResponse renewToken(@NonNull String renewToken);

    JasonWebTokenAuthentication getAuthenticationInfo();
}

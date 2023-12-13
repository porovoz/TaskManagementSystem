package com.bestapp.taskManagementSystem.service.impl;

import com.bestapp.taskManagementSystem.dto.account.JasonWebTokenProvider;
import com.bestapp.taskManagementSystem.dto.account.JasonWebTokenRequest;
import com.bestapp.taskManagementSystem.dto.account.JasonWebTokenResponse;
import com.bestapp.taskManagementSystem.dto.account.User;
import com.bestapp.taskManagementSystem.exception.invalidRegistrationParameterException.InvalidLoginPasswordException;
import com.bestapp.taskManagementSystem.exception.notFoundException.UserNotFoundException;
import com.bestapp.taskManagementSystem.security.JasonWebTokenAuthentication;
import com.bestapp.taskManagementSystem.service.AuthService;
import com.bestapp.taskManagementSystem.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JasonWebTokenProvider jasonWebTokenProvider;
    private final Map<String, String> renewStorage = new HashMap<>();

    @Override
    public JasonWebTokenResponse login(@NonNull JasonWebTokenRequest authenticationRequest) {
        final User user = userService.getByEmail(authenticationRequest.getUsername())
                .orElseThrow(() -> new UserNotFoundException(super.toString()));
        if (user.getPassword().equals(authenticationRequest.getPassword())) {
            final String actualToken = jasonWebTokenProvider.calculateActualToken(user);
            final String renewToken = jasonWebTokenProvider.calculateRenewToken(user);
            renewStorage.put(user.getEmail(), renewToken);
            return new JasonWebTokenResponse(actualToken, renewToken);
        } else {
            throw new InvalidLoginPasswordException("Incorrect password");
        }
    }

    @Override
    public JasonWebTokenResponse getActualToken(@NonNull String renewToken) {
        if (jasonWebTokenProvider.validateRenewToken(renewToken)) {
            final Claims claims = jasonWebTokenProvider.getRenewClaims(renewToken);
            final String email = claims.getSubject();
            final String saveRenewToken = renewStorage.get(email);
            if (saveRenewToken != null && saveRenewToken.equals(renewToken)) {
                final User user = userService.getByEmail(email)
                        .orElseThrow(() -> new UserNotFoundException(super.toString()));
                final String actualToken = jasonWebTokenProvider.calculateActualToken(user);
                return new JasonWebTokenResponse(actualToken, null);
            }
        }
        return new JasonWebTokenResponse(null, null);
    }


    @Override
    public JasonWebTokenResponse renewToken(@NonNull String renewToken) {
        if (jasonWebTokenProvider.validateRenewToken(renewToken)) {
            final Claims claims = jasonWebTokenProvider.getRenewClaims(renewToken);
            final String email = claims.getSubject();
            final String saveRenewToken = renewStorage.get(email);
            if (saveRenewToken != null && saveRenewToken.equals(renewToken)) {
                final User user = userService.getByEmail(email)
                        .orElseThrow(() -> new UserNotFoundException(super.toString()));
                final String actualToken = jasonWebTokenProvider.calculateActualToken(user);
                final String newRenewToken = jasonWebTokenProvider.calculateRenewToken(user);
                renewStorage.put(user.getEmail(), newRenewToken);
                return new JasonWebTokenResponse(actualToken, newRenewToken);
            }
        }
        throw new InvalidLoginPasswordException("Invalid jwt token");
    }

    @Override
    public JasonWebTokenAuthentication getAuthenticationInfo() {
        return (JasonWebTokenAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}

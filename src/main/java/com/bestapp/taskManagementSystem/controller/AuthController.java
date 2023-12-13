package com.bestapp.taskManagementSystem.controller;

import com.bestapp.taskManagementSystem.dto.account.JasonWebTokenRequest;
import com.bestapp.taskManagementSystem.dto.account.JasonWebTokenResponse;
import com.bestapp.taskManagementSystem.dto.account.RenewJasonWebTokenRequest;
import com.bestapp.taskManagementSystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JasonWebTokenResponse> login(@RequestBody JasonWebTokenRequest authRequest) {
        final JasonWebTokenResponse tokenResponse = authService.login(authRequest);
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/token")
    public ResponseEntity<JasonWebTokenResponse> getNewActualToken(@RequestBody RenewJasonWebTokenRequest request) {
        final JasonWebTokenResponse tokenResponse = authService.getActualToken(request.getRenewToken());
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/renew")
    public ResponseEntity<JasonWebTokenResponse> getNewRenewToken(@RequestBody RenewJasonWebTokenRequest request) {
        final JasonWebTokenResponse tokenResponse = authService.renewToken(request.getRenewToken());
        return ResponseEntity.ok(tokenResponse);
    }
}

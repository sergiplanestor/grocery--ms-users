package com.splanes.grocery.users.api.controller;

import com.splanes.grocery.users.api.model.request.UserSignUpRequest;
import com.splanes.grocery.users.api.model.response.AuthResponse;
import com.splanes.grocery.users.domain.service.AuthTokenProviderService;
import com.splanes.grocery.users.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthTokenProviderService tokenService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> userSignUp(@RequestBody UserSignUpRequest request) {
        String token = authHelper.authenticate(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

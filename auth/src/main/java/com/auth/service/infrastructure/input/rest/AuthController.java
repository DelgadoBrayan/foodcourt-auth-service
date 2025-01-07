package com.auth.service.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.service.application.dto.AuthRequestDto;
import com.auth.service.application.dto.AuthResponseDto;
import com.auth.service.application.dto.UserRequestDto;
import com.auth.service.application.handler.AuthenticationHandler;
import com.auth.service.application.handler.CreateOwnerHandler;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationHandler authHandler;
    private final CreateOwnerHandler ownerHandler;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDto registerRequestDto) {
        ownerHandler.saveUser(registerRequestDto);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto loginRequestDto) {
        AuthResponseDto response = authHandler.login(loginRequestDto);
        return ResponseEntity.ok(response);
    }
}
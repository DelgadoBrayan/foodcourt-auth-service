package com.auth.service.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthRequestDto {
    private String email;
    private String password;
}

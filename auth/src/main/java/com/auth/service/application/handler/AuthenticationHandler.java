package com.auth.service.application.handler;

import org.springframework.stereotype.Service;

import com.auth.service.application.dto.AuthRequestDto;
import com.auth.service.application.dto.AuthResponseDto;
import com.auth.service.application.dto.UserRequestDto;
import com.auth.service.application.mapper.AuthMapper;
import com.auth.service.application.mapper.UserMapper;
import com.auth.service.domain.api.IAuthServicePort;
import com.auth.service.domain.model.auth.AuthLogin;
import com.auth.service.domain.model.user.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationHandler {
    private final IAuthServicePort authServicePort;

    public AuthResponseDto login(AuthRequestDto authRequestDto) {
        AuthLogin auth = AuthMapper.INSTANCE.toEntity(authRequestDto);
        String token = authServicePort.validateCredentials(auth.getEmail(), auth.getPassword());
        return new AuthResponseDto(token);
    }

    public AuthResponseDto generateToken(UserRequestDto userRequestDto) {
        User user = UserMapper.INSTANCE.toEntity(userRequestDto);
        String token = authServicePort.generateToken(user);
        return new AuthResponseDto(token);
    }
}
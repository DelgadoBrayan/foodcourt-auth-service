package com.auth.service.application.handler;

import org.springframework.stereotype.Service;

import com.auth.service.application.dto.UserRequestDto;
import com.auth.service.application.dto.UserResponseDto;
import com.auth.service.application.mapper.UserMapper;
import com.auth.service.domain.api.IUserServicePort;
import com.auth.service.domain.model.user.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateOwnerHandler {
    private final IUserServicePort userServicePort;

    public UserResponseDto saveUser(UserRequestDto dto) {
        User user = UserMapper.INSTANCE.toEntity(dto);
        User savedUser = userServicePort.saveUser(user);
        return UserMapper.INSTANCE.toDto(savedUser);
    }

    public UserResponseDto findUserByEmail(String email) {
        User user = userServicePort.findUserByEmail(email);
        return UserMapper.INSTANCE.toDto(user);
    }

    public UserResponseDto findUserById(Long userId) {
        User user = userServicePort.findUserById(userId);
        return UserMapper.INSTANCE.toDto(user);
    }
}

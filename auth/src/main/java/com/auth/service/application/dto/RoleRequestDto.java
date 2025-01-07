package com.auth.service.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RoleRequestDto {
    private Long id;
    private String name;
}

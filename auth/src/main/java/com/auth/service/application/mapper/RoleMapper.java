package com.auth.service.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.auth.service.application.dto.RoleRequestDto;
import com.auth.service.domain.model.user.Role;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role toEntity(RoleRequestDto dto);

    RoleRequestDto toDto(Role role);
    
}

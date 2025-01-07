package com.auth.service.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.auth.service.application.dto.AuthRequestDto;
import com.auth.service.domain.model.auth.AuthLogin;

@Mapper
public interface AuthMapper {
       AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    AuthLogin toEntity(AuthRequestDto dto);

    AuthRequestDto toDto(AuthLogin auth);
}

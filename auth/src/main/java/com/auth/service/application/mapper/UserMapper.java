package com.auth.service.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.auth.service.application.dto.UserRequestDto;
import com.auth.service.application.dto.UserResponseDto;
import com.auth.service.domain.model.user.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "dto.firstName", target = "personalInfo.firstName")
    @Mapping(source = "dto.lastName", target = "personalInfo.lastName")
    @Mapping(source = "dto.documentId", target = "contactInfo.documentId")
    @Mapping(source = "dto.phone", target = "contactInfo.phone")
    @Mapping(source = "dto.email", target = "contactInfo.email")
    @Mapping(source = "dto.password", target = "accountInfo.password")
    @Mapping(source = "birthDate", target = "personalInfo.birthDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "roleId", target = "accountInfo.roleId")
    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequestDto dto);

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.personalInfo.firstName", target = "firstName")
    @Mapping(source = "user.personalInfo.lastName", target = "lastName")
    @Mapping(source = "user.contactInfo.email", target = "email")
    UserResponseDto toDto(User user);
}

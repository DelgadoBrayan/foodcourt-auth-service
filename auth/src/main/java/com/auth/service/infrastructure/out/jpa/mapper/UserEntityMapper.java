package com.auth.service.infrastructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.auth.service.domain.model.user.User;
import com.auth.service.infrastructure.out.jpa.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    @Mapping(source = "user.personalInfo.firstName", target = "firstName")
    @Mapping(source = "user.personalInfo.lastName", target = "lastName")
    @Mapping(source = "user.personalInfo.birthDate", target = "birthDate")
    @Mapping(source = "user.contactInfo.documentId", target = "documentId")
    @Mapping(source = "user.contactInfo.phone", target = "phone")
    @Mapping(source = "user.contactInfo.email", target = "email")
    @Mapping(source = "user.accountInfo.password", target = "password")
    @Mapping(source = "user.accountInfo.roleId", target = "roleId")
    UserEntity toEntity(User user);

    @Mapping(source = "entity.firstName", target = "personalInfo.firstName")
    @Mapping(source = "entity.lastName", target = "personalInfo.lastName")
    @Mapping(source = "entity.birthDate", target = "personalInfo.birthDate")
    @Mapping(source = "entity.documentId", target = "contactInfo.documentId")
    @Mapping(source = "entity.phone", target = "contactInfo.phone")
    @Mapping(source = "entity.email", target = "contactInfo.email")
    @Mapping(source = "entity.password", target = "accountInfo.password")
    @Mapping(source = "entity.roleId", target = "accountInfo.roleId")
    User toDomain(UserEntity entity);
}
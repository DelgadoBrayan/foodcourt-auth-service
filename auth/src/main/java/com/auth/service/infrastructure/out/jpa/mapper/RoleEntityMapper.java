package com.auth.service.infrastructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.auth.service.domain.model.user.Role;
import com.auth.service.infrastructure.out.jpa.entity.RoleEntity;

@Mapper(componentModel = "spring")
public interface RoleEntityMapper {
    RoleEntityMapper INSTANCE = Mappers.getMapper(RoleEntityMapper.class);

    RoleEntity toEntity(Role role);

    Role toDomain(RoleEntity entity);
}

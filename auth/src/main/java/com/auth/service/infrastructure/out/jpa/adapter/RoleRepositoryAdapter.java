package com.auth.service.infrastructure.out.jpa.adapter;

import com.auth.service.domain.model.user.Role;
import com.auth.service.domain.spi.IRolePersistencePort;
import com.auth.service.infrastructure.out.jpa.entity.RoleEntity;
import com.auth.service.infrastructure.out.jpa.mapper.RoleEntityMapper;
import com.auth.service.infrastructure.out.jpa.repository.RoleJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleRepositoryAdapter implements IRolePersistencePort {
    private final RoleJpaRepository repository;
    private final RoleEntityMapper mapper;

    @Override
    public Role findByName(String name) {
        RoleEntity roleEntity = repository.findByName(name);
        return mapper.toDomain(roleEntity);
    }

    @Override
    public Role save(Role role) {
        RoleEntity roleEntity = mapper.toEntity(role);
        RoleEntity savedEntity = repository.save(roleEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Role findById(Long roleId) {
        RoleEntity roleEntity = repository.findById(roleId).orElse(null);
        return roleEntity != null ? mapper.toDomain(roleEntity) : null;
    }
}
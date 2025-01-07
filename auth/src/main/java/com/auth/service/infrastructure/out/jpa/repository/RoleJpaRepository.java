package com.auth.service.infrastructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.service.infrastructure.out.jpa.entity.RoleEntity;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
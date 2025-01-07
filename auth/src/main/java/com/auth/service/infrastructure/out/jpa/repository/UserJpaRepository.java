package com.auth.service.infrastructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.service.infrastructure.out.jpa.entity.UserEntity;


public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}

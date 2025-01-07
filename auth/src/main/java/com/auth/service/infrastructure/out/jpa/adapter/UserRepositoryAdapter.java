package com.auth.service.infrastructure.out.jpa.adapter;

import com.auth.service.domain.model.user.User;
import com.auth.service.domain.spi.IUserPersistencePort;
import com.auth.service.infrastructure.out.jpa.entity.UserEntity;
import com.auth.service.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.auth.service.infrastructure.out.jpa.repository.UserJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryAdapter implements IUserPersistencePort {
    private final UserJpaRepository repository;
    private final UserEntityMapper mapper;
    @Override
    public User findUserByEmail(String email) {
        UserEntity entity = repository.findByEmail(email);
        return mapper.toDomain(entity);
    }
    @Override
    public User findUserById(Long userId) {
        UserEntity entity = repository.findById(userId).orElse(null);
        return entity != null ? mapper.toDomain(entity) : null;
    }
    @Override
    public User saveUser(User user) {
        UserEntity entity = mapper.toEntity(user);
        UserEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }
   
}

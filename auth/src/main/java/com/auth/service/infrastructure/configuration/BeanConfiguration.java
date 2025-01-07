package com.auth.service.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.auth.service.domain.api.IRestaurantServicePort;
import com.auth.service.domain.api.IRoleServicePort;
import com.auth.service.domain.api.IUserServicePort;
import com.auth.service.domain.spi.IRestaurantPersistencePort;
import com.auth.service.domain.spi.IRolePersistencePort;
import com.auth.service.domain.spi.IUserPersistencePort;
import com.auth.service.domain.usecase.CreateOwnerUseCase;
import com.auth.service.domain.usecase.CreateRestaurantUseCase;
import com.auth.service.domain.usecase.RoleUseCase;
import com.auth.service.infrastructure.out.jpa.adapter.RestaurantRepositoryAdapter;
import com.auth.service.infrastructure.out.jpa.adapter.RoleRepositoryAdapter;
import com.auth.service.infrastructure.out.jpa.adapter.UserRepositoryAdapter;
import com.auth.service.infrastructure.out.jpa.mapper.RestaurantEntityMapper;
import com.auth.service.infrastructure.out.jpa.mapper.RoleEntityMapper;
import com.auth.service.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.auth.service.infrastructure.out.jpa.repository.RestaurantJpaRepository;
import com.auth.service.infrastructure.out.jpa.repository.RoleJpaRepository;
import com.auth.service.infrastructure.out.jpa.repository.UserJpaRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper userEntityMapper;
    private final RoleJpaRepository roleJpaRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Bean
    IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantRepositoryAdapter(restaurantJpaRepository, restaurantEntityMapper);
    }

    @Bean
    IRestaurantServicePort restaurantServicePort() {
        return new CreateRestaurantUseCase(restaurantPersistencePort(), userPersistencePort(),rolePersistencePort());
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserRepositoryAdapter(userJpaRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new CreateOwnerUseCase(userPersistencePort());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleRepositoryAdapter(roleJpaRepository, roleEntityMapper);
    }

    @Bean
    public IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }

}

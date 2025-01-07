package com.auth.service.infrastructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.service.infrastructure.out.jpa.entity.RestaurantEntity;

public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, Long> {
}

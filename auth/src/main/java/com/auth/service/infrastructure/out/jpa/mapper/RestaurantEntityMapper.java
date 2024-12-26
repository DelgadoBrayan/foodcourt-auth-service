package com.auth.service.infrastructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.auth.service.domain.model.Restaurant;
import com.auth.service.infrastructure.out.jpa.entity.RestaurantEntity;

@Mapper
public interface RestaurantEntityMapper {
    RestaurantEntityMapper INSTANCE = Mappers.getMapper(RestaurantEntityMapper.class);

    RestaurantEntity toEntity(Restaurant restaurant);
    Restaurant toModel(RestaurantEntity restaurantEntity);
}

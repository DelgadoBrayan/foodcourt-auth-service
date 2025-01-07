package com.auth.service.infrastructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.auth.service.domain.model.restaurant.Restaurant;
import com.auth.service.infrastructure.out.jpa.entity.RestaurantEntity;

@Mapper(componentModel = "spring")
public interface RestaurantEntityMapper {
    RestaurantEntityMapper INSTANCE = Mappers.getMapper(RestaurantEntityMapper.class);
    
    @Mapping(source = "urlLogo", target = "logoUrl")
    @Mapping(source = "ownerId", target = "owner.id")
    RestaurantEntity toEntity(Restaurant restaurant);

    @Mapping(source = "logoUrl", target = "urlLogo")
    @Mapping(source = "owner.id", target = "ownerId")
    Restaurant toDomain(RestaurantEntity entity);
}
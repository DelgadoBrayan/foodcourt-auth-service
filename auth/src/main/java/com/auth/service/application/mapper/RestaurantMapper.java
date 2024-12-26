package com.auth.service.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.auth.service.application.dto.RestaurantDto;
import com.auth.service.domain.model.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant toRestaurant(RestaurantDto restaurantDto);
    RestaurantDto toRestaurantDto(Restaurant restaurant);
}

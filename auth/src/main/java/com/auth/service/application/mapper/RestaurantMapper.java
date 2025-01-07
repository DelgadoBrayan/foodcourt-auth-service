package com.auth.service.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.auth.service.application.dto.RestaurantRequestDto;
import com.auth.service.application.dto.RestaurantResponseDto;
import com.auth.service.domain.model.restaurant.Restaurant;

@Mapper
public interface RestaurantMapper {
      RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "urlLogo", source = "urlLogo")
    @Mapping(target = "ownerId", source = "ownerId")
    Restaurant toEntity(RestaurantRequestDto dto);

    @Mapping(target = "logoUrl", source = "urlLogo")
    RestaurantResponseDto toDto(Restaurant restaurant);
}

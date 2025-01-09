package com.auth.service.application.handler;

import java.util.List;

import org.springframework.stereotype.Service;

import com.auth.service.application.dto.RestaurantRequestDto;
import com.auth.service.application.dto.RestaurantResponseDto;
import com.auth.service.application.dto.RestaurantResponseList;
import com.auth.service.application.mapper.RestaurantMapper;
import com.auth.service.domain.model.restaurant.Restaurant;
import com.auth.service.domain.usecase.CreateRestaurantUseCase;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateRestaurantHandler {
    private final CreateRestaurantUseCase createRestaurantUseCase;


    public RestaurantResponseDto saveRestaurant(RestaurantRequestDto dto) {
        Restaurant restaurant = RestaurantMapper.INSTANCE.toEntity(dto);
        Restaurant savedRestaurant = createRestaurantUseCase.saveRestaurant(restaurant);
        return RestaurantMapper.INSTANCE.toDto(savedRestaurant);
    }

    public RestaurantResponseDto findRestaurantById(Long restaurantId) {
        Restaurant restaurant = createRestaurantUseCase.findRestaurantById(restaurantId);
        return RestaurantMapper.INSTANCE.toDto(restaurant);
    }

    public List<RestaurantResponseList> getRestaurantsOrderedAndPaginated(int page, int size) {
        List<Restaurant> restaurants = createRestaurantUseCase.getAllRestaurants(page, size);

        return RestaurantMapper.INSTANCE.toResponseList(restaurants);
    }
}
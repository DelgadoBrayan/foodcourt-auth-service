package com.auth.service.application.handler;

import org.springframework.stereotype.Service;
import com.auth.service.application.dto.RestaurantDto;
import com.auth.service.application.mapper.RestaurantMapper;
import com.auth.service.domain.model.Restaurant;
import com.auth.service.domain.usecase.CreateRestaurantUseCase;

@Service
public class RestaurantHandler {
    private final CreateRestaurantUseCase createRestaurantUseCase;

    public RestaurantHandler(CreateRestaurantUseCase createRestaurantUseCase) {
        this.createRestaurantUseCase = createRestaurantUseCase;
    }

    public void createRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = RestaurantMapper.INSTANCE.toRestaurant(restaurantDto);
        createRestaurantUseCase.execute(restaurant);
    }
}

package com.auth.service.domain.usecase;

import com.auth.service.domain.model.Restaurant;
import com.auth.service.domain.spi.IRestaurantPersistencePort;

public class CreateRestaurantUseCase {
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public CreateRestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    public void execute(Restaurant restaurant) {
        if (restaurantPersistencePort.existsByOwnerId(restaurant.getOwnerId())) { 
            throw new IllegalArgumentException("Owner does not have the correct role"); 
        } 
        if (!restaurant.getNit().matches("\\d+")) { 
            throw new IllegalArgumentException("NIT should be numeric"); 
        } 
        if (!restaurant.getPhone().matches("\\+?\\d{1,13}")) { 
            throw new IllegalArgumentException("Phone should be numeric and can contain +"); 
        } 
        if (restaurant.getName().matches("\\d+")) {
             throw new IllegalArgumentException("Restaurant name cannot contain only numbers");
        }
        restaurantPersistencePort.saveRestaurant(restaurant);
    }
}

package com.auth.service.domain.spi;

import com.auth.service.domain.model.Restaurant;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    boolean existsByOwnerId(Long ownerId);
}

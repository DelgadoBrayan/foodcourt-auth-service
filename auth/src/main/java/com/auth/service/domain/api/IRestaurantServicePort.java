package com.auth.service.domain.api;


import com.auth.service.domain.model.restaurant.Restaurant;

public interface IRestaurantServicePort {
    Restaurant saveRestaurant(Restaurant restaurant);
    Restaurant findRestaurantById(Long restaurantId);
}

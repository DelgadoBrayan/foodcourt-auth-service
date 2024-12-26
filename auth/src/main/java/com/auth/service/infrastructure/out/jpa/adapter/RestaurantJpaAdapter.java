package com.auth.service.infrastructure.out.jpa.adapter;

import org.springframework.stereotype.Service;

import com.auth.service.domain.model.Restaurant;
import com.auth.service.domain.spi.IRestaurantPersistencePort;
import com.auth.service.infrastructure.out.jpa.mapper.RestaurantEntityMapper;
import com.auth.service.infrastructure.out.jpa.repository.RestaurantRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    @Override
    public boolean existsByOwnerId(Long ownerId) {
        return restaurantRepository.existsByOwnerId(ownerId);
    }
    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }
 
}

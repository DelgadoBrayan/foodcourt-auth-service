package com.auth.service.infrastructure.input.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.service.application.dto.RestaurantRequestDto;
import com.auth.service.application.dto.RestaurantResponseList;
import com.auth.service.application.handler.CreateRestaurantHandler;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final CreateRestaurantHandler restaurantHandler;

   

    @PostMapping
    public ResponseEntity<String> createRestaurant(@RequestBody RestaurantRequestDto restaurantDto) {
        restaurantHandler.saveRestaurant(restaurantDto);
        return ResponseEntity.ok("Restaurant created successfully");
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponseList>> getRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<RestaurantResponseList> restaurants = restaurantHandler.getRestaurantsOrderedAndPaginated(page, size);
        return ResponseEntity.ok(restaurants);
    }
}
package com.auth.service.infrastructure.input.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.service.application.dto.RestaurantDto;
import com.auth.service.application.handler.RestaurantHandler;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/admin/restaurants")
@AllArgsConstructor
public class RestaurantController {
    private final RestaurantHandler restaurantHandler;

    @PostMapping
    public ResponseEntity<Void> createRestaurant(@Valid @RequestBody RestaurantDto restaurantDto) {
        restaurantHandler.createRestaurant(restaurantDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    
}

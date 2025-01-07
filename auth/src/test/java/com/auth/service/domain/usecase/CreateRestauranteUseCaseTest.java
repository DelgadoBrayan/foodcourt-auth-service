package com.auth.service.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.auth.service.domain.model.restaurant.Restaurant;
import com.auth.service.domain.spi.IRestaurantPersistencePort;

class CreateRestauranteUseCaseTest {
   @Mock
   private IRestaurantPersistencePort restaurantPersistencePort;

    @InjectMocks
    private CreateRestaurantUseCase createRestaurantUseCase;

    private Restaurant restaurant;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        restaurant = new Restaurant(1L, "Restaurant", "123456789", "Calle 123", "3001234567", "http://example.com/logo.png", 1L);
        
    }

    @Test
    void testExecute_OwnerDoesNotHaveTheCorrectRole(){
        when(restaurantPersistencePort.findRestaurantById(restaurant.getOwnerId()));
       IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { 
        createRestaurantUseCase.saveRestaurant(restaurant);
    }); 
    assertEquals("Owner does not have the correct role", exception.getMessage()); 
    verify(restaurantPersistencePort, times(1)).findRestaurantById(restaurant.getOwnerId()); 
    verify(restaurantPersistencePort, never()).saveRestaurant(any(Restaurant.class)); 
    }

    @Test
    void testExecute_InvalidNit(){
        restaurant.setNit("123456789a");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { 
            createRestaurantUseCase.saveRestaurant(restaurant); 
        }); 
        assertEquals("NIT should be numeric", exception.getMessage()); 
        verify(restaurantPersistencePort, never()).saveRestaurant(any(Restaurant.class)); 
    }

    @Test
    void testExecute_InvalidPhone(){
        restaurant.setPhone("3001234567a");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { 
            createRestaurantUseCase.saveRestaurant(restaurant); 
        }); 
        assertEquals("Phone should be numeric and can contain +", exception.getMessage()); 
        verify(restaurantPersistencePort, never()).saveRestaurant(any(Restaurant.class)); 
    }

    @Test
    void testExecute_InvalidName(){
        restaurant.setName("123456789");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { 
            createRestaurantUseCase.saveRestaurant(restaurant); 
        }); 
        assertEquals("Restaurant name cannot contain only numbers", exception.getMessage()); 
        verify(restaurantPersistencePort, never()).saveRestaurant(any(Restaurant.class)); 
    }

    @Test
    void testExecute_Success(){
        when(restaurantPersistencePort.findRestaurantById(restaurant.getOwnerId()));
        createRestaurantUseCase.saveRestaurant(restaurant);
        verify(restaurantPersistencePort, times(1)).findRestaurantById(restaurant.getOwnerId());
        verify(restaurantPersistencePort, times(1)).saveRestaurant(restaurant);
    }
}

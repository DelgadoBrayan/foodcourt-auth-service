package com.auth.service.application.handler;


import com.auth.service.application.dto.RestaurantDto;
import com.auth.service.application.mapper.RestaurantMapper;
import com.auth.service.domain.model.Restaurant;
import com.auth.service.domain.usecase.CreateRestaurantUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantHandlerTest {

    @Mock
    private CreateRestaurantUseCase createRestaurantUseCase;

    @InjectMocks
    private RestaurantHandler restaurantHandler;

    private RestaurantDto restaurantDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantDto = new RestaurantDto();
        restaurantDto.setId(1L);
        restaurantDto.setName("La Parrilla");
        restaurantDto.setNit("123456789");
        restaurantDto.setAddress("Calle Falsa 123");
        restaurantDto.setPhone("+573005698325");
        restaurantDto.setUrlLogo("http://example.com/logo.png");
        restaurantDto.setOwnerId(1L);
    }

    @Test
    void testCreateRestaurant() {
        restaurantHandler.createRestaurant(restaurantDto);

        Restaurant restaurant = RestaurantMapper.INSTANCE.toRestaurant(restaurantDto);
        verify(createRestaurantUseCase, times(1)).execute(any(restaurant.getClass()));
    }
}


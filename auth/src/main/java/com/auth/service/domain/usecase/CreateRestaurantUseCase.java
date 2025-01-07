package com.auth.service.domain.usecase;

import org.springframework.stereotype.Service;

import com.auth.service.domain.api.IRestaurantServicePort;
import com.auth.service.domain.model.restaurant.Restaurant;
import com.auth.service.domain.model.user.Role;
import com.auth.service.domain.model.user.User;
import com.auth.service.domain.spi.IRestaurantPersistencePort;
import com.auth.service.domain.spi.IRolePersistencePort;
import com.auth.service.domain.spi.IUserPersistencePort;
import com.auth.service.infrastructure.exception.InvalidRestaurantException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateRestaurantUseCase implements IRestaurantServicePort{
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserPersistencePort userPersistencePort ;
    private final IRolePersistencePort rolePersistencePort;
    @Override
    public Restaurant findRestaurantById(Long restaurantId) {
        return restaurantPersistencePort.findRestaurantById(restaurantId);
    }
    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        validateRestaurant(restaurant);
        validateOwner(restaurant.getOwnerId());

        return restaurantPersistencePort.saveRestaurant(restaurant);
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (restaurant.getName() == null || restaurant.getName().isEmpty()) {
            throw new InvalidRestaurantException("El nombre del restaurante no debe estar vacío o nulo");
        }
        if (restaurant.getNit() == null || restaurant.getNit().isEmpty()) {
            throw new InvalidRestaurantException("El NIT del restaurante no debe estar vacío o nulo");
        }
        if (restaurant.getAddress() == null || restaurant.getAddress().isEmpty()) {
            throw new InvalidRestaurantException("La dirección del restaurante no debe estar vacía o nula");
        }
        if (restaurant.getPhone() == null || restaurant.getPhone().isEmpty()) {
            throw new InvalidRestaurantException("El teléfono del restaurante no debe estar vacío o nulo");
        }
        if (restaurant.getUrlLogo() == null || restaurant.getUrlLogo().isEmpty()) {
            throw new InvalidRestaurantException("La URL del logo del restaurante no debe estar vacía o nula");
        }
        if (restaurant.getOwnerId() == null) {
            throw new InvalidRestaurantException("El ID del propietario no debe ser nulo");
        }
        if (!restaurant.getNit().matches("\\d+")) {
            throw new InvalidRestaurantException("El NIT debe contener únicamente caracteres numéricos");
        }
        if (!restaurant.getPhone().matches("\\+?\\d{1,13}")) {
            throw new InvalidRestaurantException("El teléfono debe ser numérico, con un máximo de 13 caracteres, y puede incluir '+'");
        }
        if (restaurant.getName().matches("\\d+")) {
            throw new InvalidRestaurantException("El nombre del restaurante no puede contener únicamente números");
        }
    }
    
    private void validateOwner(Long ownerId) {
        User owner = userPersistencePort.findUserById(ownerId);
        if (owner == null) {
            throw new InvalidRestaurantException("Propietario no encontrado");
        }
        Role role = rolePersistencePort.findById(owner.getAccountInfo().getRoleId());
        if (role == null || !"OWNER".equals(role.getName())) {
            throw new InvalidRestaurantException("No puesdes realizar esta accion por tu rol");
        }
    }
    

}
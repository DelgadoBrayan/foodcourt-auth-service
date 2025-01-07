package com.auth.service.infrastructure.exception;

public class InvalidRestaurantException extends RuntimeException {
    public InvalidRestaurantException(String messsage){
        super(messsage);
    }
}


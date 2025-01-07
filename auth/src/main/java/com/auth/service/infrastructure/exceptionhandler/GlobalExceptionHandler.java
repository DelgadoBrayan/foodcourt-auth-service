package com.auth.service.infrastructure.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth.service.infrastructure.exception.InvalidRestaurantException;
import com.auth.service.infrastructure.exception.InvalidUserException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<String> handleInvalidUserException(InvalidUserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidRestaurantException.class)
    public ResponseEntity<String> handlerRestaurantExeption(InvalidRestaurantException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}


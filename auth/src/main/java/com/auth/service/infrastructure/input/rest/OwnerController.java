package com.auth.service.infrastructure.input.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.service.application.dto.OwnerDto;
import com.auth.service.application.handler.OwnerHandler;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/admin/owners")
@AllArgsConstructor
public class OwnerController {
    private final OwnerHandler ownerHandler;

    @PostMapping
    public ResponseEntity<Void> registerOwner(@Valid @RequestBody OwnerDto ownerDTO) {
        ownerHandler.registerOwner(ownerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    
}

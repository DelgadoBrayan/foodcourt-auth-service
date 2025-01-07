package com.auth.service.infrastructure.input.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.auth.service.application.dto.RoleRequestDto;
import com.auth.service.application.handler.RoleHandler;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleHandler roleHandler;

    @GetMapping("/{name}")
    public ResponseEntity<RoleRequestDto> findByName(@PathVariable String name) {
        RoleRequestDto roleRequestDto = roleHandler.findByName(name);
        return ResponseEntity.ok(roleRequestDto);
    }

    @PostMapping
    public ResponseEntity<RoleRequestDto> save(@RequestBody RoleRequestDto roleRequestDto) {
        RoleRequestDto savedRole = roleHandler.save(roleRequestDto);
        return ResponseEntity.ok(savedRole);
    }
}

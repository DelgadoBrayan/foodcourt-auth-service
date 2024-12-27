package com.auth.service.application.handler;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.service.application.dto.OwnerDto;
import com.auth.service.application.mapper.OwnerMapper;
import com.auth.service.domain.model.Owner;
import com.auth.service.domain.usecase.RegisterOwnerUseCase;

@Service
public class OwnerHandler {
    private final RegisterOwnerUseCase registerOwnerUseCase;
    private final BCryptPasswordEncoder passwordEncoder;

    public OwnerHandler(RegisterOwnerUseCase registerOwnerUseCase, BCryptPasswordEncoder passwordEncoder) {
        this.registerOwnerUseCase = registerOwnerUseCase;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerOwner(OwnerDto ownerDto) {
        Owner owner = OwnerMapper.INSTANCE.toOwner(ownerDto); 
        owner.getAccountInfo().setPassword(passwordEncoder.encode(ownerDto.getPassword())); 
        owner.getAccountInfo().setRole("OWNER"); 
        registerOwnerUseCase.execute(owner);
    }
}

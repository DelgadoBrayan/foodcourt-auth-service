package com.auth.service.infrastructure.out.jpa.adapter;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.auth.service.domain.model.Owner;
import com.auth.service.domain.spi.IOwnerPersistencePort;
import com.auth.service.infrastructure.out.jpa.repository.OwnerRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class OwnerJpaAdapter implements IOwnerPersistencePort {
    private final OwnerRepository ownerRepository;

    @Override public Optional<Owner> findByEmail(String email) { 
        return ownerRepository.findByEmail(email); 
    } 
    @Override public void saveOwner(Owner owner) {
         ownerRepository.save(owner);
    }
}

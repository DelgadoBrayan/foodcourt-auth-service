package com.auth.service.domain.spi;

import java.util.Optional;

import com.auth.service.domain.model.Owner;

public interface IOwnerPersistencePort {
    
    Optional<Owner> findByEmail(String email); 
    void saveOwner(Owner owner);
}

package com.auth.service.domain.usecase;

import com.auth.service.domain.model.Owner;
import com.auth.service.domain.spi.IOwnerPersistencePort;

public class RegisterOwnerUseCase {
    private final IOwnerPersistencePort ownerPersistencePort;

    public RegisterOwnerUseCase(IOwnerPersistencePort ownerPersistencePort) {
        this.ownerPersistencePort = ownerPersistencePort;
    }

    public void execute(Owner owner) {
        if (ownerPersistencePort.findByEmail(owner.getContactInfo().getEmail()).isPresent()) {
            throw new IllegalArgumentException("Owner already exists");
        }

        if (!owner.isAdult()) {
            throw new IllegalArgumentException("Owner must be an adult");
        }
        ownerPersistencePort.saveOwner(owner);
    }
}
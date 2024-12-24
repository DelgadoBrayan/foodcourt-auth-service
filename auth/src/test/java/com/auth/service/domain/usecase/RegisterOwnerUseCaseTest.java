package com.auth.service.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.auth.service.domain.model.AccountInfo;
import com.auth.service.domain.model.ContactInfo;
import com.auth.service.domain.model.Owner;
import com.auth.service.domain.model.PersonalInfo;
import com.auth.service.domain.spi.IOwnerPersistencePort;

 class RegisterOwnerUseCaseTest {
    @Mock
    private IOwnerPersistencePort ownerPersistencePort;

    @InjectMocks
    private RegisterOwnerUseCase registerOwnerUseCase;

    private Owner owner;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        PersonalInfo personalInfo = new PersonalInfo("John", "Doe", LocalDate.of(1990, 1, 1));
        ContactInfo contactInfo = new ContactInfo("1061016730", "+513170501796","bd@gmail.com");
        AccountInfo accountInfo = new AccountInfo("password");
        owner = new Owner(1L,personalInfo, contactInfo, accountInfo);
    }

    
    @Test 
    void testExecute_OwnerAlreadyExists() { 
        when(ownerPersistencePort.findByEmail(owner.getContactInfo().getEmail())).thenReturn(Optional.of(owner)); 
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { 
            registerOwnerUseCase.execute(owner); }); 
        
        assertEquals("Owner already exists", exception.getMessage()); 
        verify(ownerPersistencePort, times(1)).findByEmail(owner.getContactInfo().getEmail()); 
        verify(ownerPersistencePort, never()).saveOwner(any(Owner.class));
    }

    @Test 
    void testExecute_OwnerNotAdult() { 
        owner.getPersonalInfo().setBirthDate(LocalDate.of(2010, 1, 1)); 
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { 
            registerOwnerUseCase.execute(owner); 
        }); 
        assertEquals("Owner must be an adult", exception.getMessage()); 
        verify(ownerPersistencePort, never()).saveOwner(any(Owner.class)); 
    }

    @Test  
    void testExecute_Success() { 
        when(ownerPersistencePort.findByEmail(owner.getContactInfo().getEmail())).thenReturn(Optional.empty()); 
        registerOwnerUseCase.execute(owner); 
        verify(ownerPersistencePort, times(1)).findByEmail(owner.getContactInfo().getEmail()); 
        verify(ownerPersistencePort, times(1)).saveOwner(owner); 
    }

}

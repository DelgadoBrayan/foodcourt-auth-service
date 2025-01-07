package com.auth.service.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.auth.service.domain.model.user.AccountInfo;
import com.auth.service.domain.model.user.ContactInfo;
import com.auth.service.domain.model.user.PersonalInfo;
import com.auth.service.domain.model.user.User;
import com.auth.service.domain.spi.IUserPersistencePort;

 class RegisterOwnerUseCaseTest {
    @Mock
    private IUserPersistencePort ownerPersistencePort;

    @InjectMocks
    private CreateOwnerUseCase registerOwnerUseCase;

    private User owner;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        PersonalInfo personalInfo = new PersonalInfo("John", "Doe", LocalDate.of(1990, 1, 1));
        ContactInfo contactInfo = new ContactInfo(1061016730L, 573170501796L, "bd@gmail.com");
        AccountInfo accountInfo = new AccountInfo("password", 1L);
        owner = new User(1L,personalInfo, contactInfo, accountInfo);
    }

    
    @Test 
    void testExecute_OwnerAlreadyExists() { 
        when(ownerPersistencePort.findUserByEmail(owner.getContactInfo().getEmail())).thenReturn(owner); 
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { 
            registerOwnerUseCase.saveUser(owner);
        }); 
        
        assertEquals("Owner already exists", exception.getMessage()); 
        verify(ownerPersistencePort, times(1)).findUserByEmail(owner.getContactInfo().getEmail()); 
        verify(ownerPersistencePort, never()).saveUser(any(User.class));
    }

    @Test 
    void testExecute_OwnerNotAdult() { 
        owner.getPersonalInfo().setBirthDate(LocalDate.of(2010, 1, 1)); 
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { 
            registerOwnerUseCase.saveUser(owner); 
        }); 
        assertEquals("Owner must be an adult", exception.getMessage()); 
        verify(ownerPersistencePort, never()).saveUser(any(User.class)); 
    }

    @Test  
    void testExecute_Success() { 
        when(ownerPersistencePort.findUserByEmail(owner.getContactInfo().getEmail())).thenReturn(owner); 
        registerOwnerUseCase.saveUser(owner); 
        verify(ownerPersistencePort, times(1)).findUserByEmail(owner.getContactInfo().getEmail()); 
        verify(ownerPersistencePort, times(1)).saveUser(owner); 
    }

}

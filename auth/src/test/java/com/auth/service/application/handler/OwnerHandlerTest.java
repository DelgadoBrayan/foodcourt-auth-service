package com.auth.service.application.handler;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.auth.service.application.dto.OwnerDto;
import com.auth.service.application.mapper.OwnerMapper;
import com.auth.service.domain.model.Owner;
import com.auth.service.domain.usecase.RegisterOwnerUseCase;

class OwnerHandlerTest {
        @Mock
        private RegisterOwnerUseCase registerOwnerUseCase;

        @Mock
        private BCryptPasswordEncoder passwordEncoder;

        @InjectMocks
        private OwnerHandler ownerHandler;

        private OwnerDto ownerDto;

        @BeforeEach
        public void  setUp(){
            MockitoAnnotations.openMocks(this);
            ownerDto = new OwnerDto();
            ownerDto.setFirstName("John");
            ownerDto.setLastName("Doe");
            ownerDto.setDocumentId("1061016730");
            ownerDto.setPhone("3001234567");
            ownerDto.setBirthDate(LocalDate.of(2005, 3, 23));
            ownerDto.setEmail("bd@gmail.com");
            ownerDto.setPassword("password");
        }

        @Test
        void registerOwnerTest(){
            when(passwordEncoder.encode(ownerDto.getPassword())).thenReturn("encryptedPassword");
            ownerHandler.registerOwner(ownerDto);

            verify(passwordEncoder, times(1)).encode(ownerDto.getPassword());
            Owner owner = OwnerMapper.INSTANCE.toOwner(ownerDto);
            owner.getAccountInfo().setPassword("encryptedPassword");
            verify(registerOwnerUseCase, times(1)).execute(owner);
        }
    
}

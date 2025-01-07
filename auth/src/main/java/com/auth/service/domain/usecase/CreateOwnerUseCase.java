package com.auth.service.domain.usecase;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.service.domain.api.IUserServicePort;
import com.auth.service.domain.model.user.AccountInfo;
import com.auth.service.domain.model.user.ContactInfo;
import com.auth.service.domain.model.user.PersonalInfo;
import com.auth.service.domain.model.user.User;
import com.auth.service.domain.spi.IUserPersistencePort;
import com.auth.service.infrastructure.exception.InvalidUserException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateOwnerUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    @Override
    public User findUserByEmail(String email) {
        return userPersistencePort.findUserByEmail(email);
    }

    @Override
    public User findUserById(Long userId) {
        return userPersistencePort.findUserById(userId);
    }

    @Override
    public User saveUser(User user) {
        ContactInfo contactInfo = user.getContactInfo();
        AccountInfo accountInfo = user.getAccountInfo();
        PersonalInfo personalInfo = user.getPersonalInfo();

        validateContactInfo(contactInfo);

        if (personalInfo.getBirthDate() == null || !isOfLegalAge(personalInfo.getBirthDate())) {
            throw new InvalidUserException("El usuario debe ser mayor de edad y la fecha de nacimiento no puede ser nula");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(accountInfo.getPassword());
        accountInfo.setPassword(encodedPassword);

        return userPersistencePort.saveUser(user);
    }

    private boolean isOfLegalAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
    }

    private void validateContactInfo(ContactInfo contactInfo) {
        
        if (contactInfo.getDocumentId() == null || !contactInfo.getDocumentId().toString().matches("\\d+")) {
            throw new InvalidUserException("El documento de identidad debe ser numérico y no puede ser nulo.");
        }

        String phoneRegex = "^\\+?(\\d{9,13})$";
        if (!Pattern.compile(phoneRegex).matcher(contactInfo.getPhone().toString()).matches()) {
            throw new InvalidUserException("El número de teléfono es inválido.");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!Pattern.compile(emailRegex).matcher(contactInfo.getEmail()).matches()) {
            throw new InvalidUserException("El correo electrónico es inválido.");
        }
    }
}
package com.auth.service.domain.spi;

import com.auth.service.domain.model.user.User;

public interface IAuthPersistencePort {
    String validateCredentials(String email, String password);
    String generateToken(User user);
}

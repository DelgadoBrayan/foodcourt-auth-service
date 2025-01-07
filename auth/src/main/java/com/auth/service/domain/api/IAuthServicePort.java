package com.auth.service.domain.api;

import com.auth.service.domain.model.user.User;

public interface IAuthServicePort {
    String validateCredentials(String email, String password);
    String generateToken(User user);
}

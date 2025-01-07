package com.auth.service.domain.usecase;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.auth.service.domain.api.IAuthServicePort;
import com.auth.service.domain.model.user.User;
import com.auth.service.domain.spi.IUserPersistencePort;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationUseCase implements IAuthServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final byte[] secretKey;
    private static final long EXPIRATION_TIME = 86400000;

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getContactInfo().getEmail())
                .claim("role", user.getAccountInfo().getRoleId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String validateCredentials(String email, String password) {
        User user = userPersistencePort.findUserByEmail(email);
        if (user == null || !passwordMatches(password, user.getAccountInfo().getPassword())) {
            throw new IllegalArgumentException("credenciales invalidas");
        }
        return generateToken(user);
    }

    private boolean passwordMatches(String rawPassword, String storedPassword) {
        return BCrypt.checkpw(rawPassword, storedPassword);
    }
}

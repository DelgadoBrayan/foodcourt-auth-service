package com.auth.service.domain.model.auth;

import java.time.LocalDate;

public class Authentication {
    private String token;
    private LocalDate expirationDate;
    public Authentication(String token, LocalDate expirationDate) {
        this.token = token;
        this.expirationDate = expirationDate;
    }

    public String getToken() {return token;}
    public void setToken(String token) {this.token = token;}

    public LocalDate getExpirationDate() {return expirationDate;}
    public void setExpirationDate(LocalDate expirationDate) {this.expirationDate = expirationDate;}
}

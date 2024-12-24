package com.auth.service.domain.model;

public class AccountInfo {

    private String password;
    private String role = "propietario";

    public AccountInfo() {}

    public AccountInfo(String password) {
        this.password = password;
        this.role = "propietario";
    }

    // Getters y Setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

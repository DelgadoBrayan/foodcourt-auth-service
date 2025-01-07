package com.auth.service.application.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private Long documentId;
    private Long phone;
    private String birthDate;
    private String email;
    private String password;
    private Long roleId;
}

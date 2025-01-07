package com.auth.service.application.dto;

import lombok.Data;

@Data
public class RestaurantRequestDto {
    private String name;
    private String nit;
    private String address;
    private String phone;
    private String urlLogo;
    private Long ownerId;
}

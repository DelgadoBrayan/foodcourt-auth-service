package com.auth.service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String nit;
    private String address;
    private String phone;
    private String logoUrl;
    private Long ownerId;

}

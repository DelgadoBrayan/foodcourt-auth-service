package com.auth.service.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RestaurantDto {
    private Long id;
    @NotNull 
    @Size(min = 1, max = 50) 
    private String name; 
    
    @NotNull 
    @Pattern(regexp = "\\d+", message = "NIT should be numeric") 
    private String nit; 
    
    @NotNull 
    @Size(min = 1, max = 100) 
    private String address; 
    
    @NotNull 
    @Pattern(regexp = "\\+?\\d{1,13}", message = "Phone should be numeric and can contain +") 
    private String phone; 
    
    @NotNull 
    private String urlLogo; 
    
    @NotNull 
    private Long ownerId;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getNit() {return nit;}
    public void setNit(String nit) {this.nit = nit;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    public String getUrlLogo() {return urlLogo;}
    public void setUrlLogo(String urlLogo) {this.urlLogo = urlLogo;}

    public Long getOwnerId() {return ownerId;}
    public void setOwnerId(Long ownerId) {this.ownerId = ownerId;} 
}

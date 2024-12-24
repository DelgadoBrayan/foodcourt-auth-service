package com.auth.service.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.auth.service.application.dto.OwnerDto;
import com.auth.service.domain.model.Owner;

@Mapper
public interface OwnerMapper {
    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class); 
    @Mapping(source = "birthDate", target = "personalInfo.birthDate") 
    @Mapping(source = "firstName", target = "personalInfo.firstName") 
    @Mapping(source = "lastName", target = "personalInfo.lastName") 
    @Mapping(source = "documentId", target = "contactInfo.documentId") 
    @Mapping(source = "phone", target = "contactInfo.phone") 
    @Mapping(source = "email", target = "contactInfo.email") 
    @Mapping(source = "password", target = "accountInfo.password") 
    Owner toOwner(OwnerDto ownerDto); 
    @Mapping(source = "personalInfo.birthDate", target = "birthDate") 
    @Mapping(source = "personalInfo.firstName", target = "firstName") 
    @Mapping(source = "personalInfo.lastName", target = "lastName") 
    @Mapping(source = "contactInfo.documentId", target = "documentId") 
    @Mapping(source = "contactInfo.phone", target = "phone") 
    @Mapping(source = "contactInfo.email", target = "email") 
    @Mapping(source = "accountInfo.password", target = "password") 
    OwnerDto toOwnerDTO(Owner owner);
}
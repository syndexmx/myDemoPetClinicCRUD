package com.github.syndexmx.demopetclinic.controller.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controller.dtos.OwnerDto;
import com.github.syndexmx.demopetclinic.domain.OwnerFields;
import com.github.syndexmx.demopetclinic.domain.Owner;

import java.util.Random;


@TemplatedAnnotation
public class OwnerDtoMapper {

    public static OwnerDto ownerToOwnerDto(Owner owner) {
        final OwnerDto ownerDto = OwnerDto.builder()
                .id(owner.getId())
                .name(owner.getName())
                .phone(owner.getPhone())
                .address(owner.getAddress())
                .ownerFieldContent(owner.getOwnerFields().toString())
                .build();
        return ownerDto;
    }

    public static Owner ownerDtoToOwner(OwnerDto ownerDto) {
        Owner owner = Owner.builder()
                .id(ownerDto.getId())
                .name(ownerDto.getName())
                .phone(ownerDto.getPhone())
                .address(ownerDto.getAddress())
                .ownerFields(OwnerFields.valueOf(ownerDto.getOwnerFieldContent()))
                .build();
        return owner;
    }

    public static Owner ownerDtoNoIdToOwner(OwnerDto ownerDto) {
        Random random = new Random();
        Owner owner = Owner.builder()
                .id(random.nextLong())
                .name(ownerDto.getName())
                .phone(ownerDto.getPhone())
                .address(ownerDto.getAddress())
                .ownerFields(OwnerFields.valueOf(ownerDto.getOwnerFieldContent()))
                .build();
        return owner;
    }

}

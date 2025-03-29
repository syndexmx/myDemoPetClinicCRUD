package com.github.syndexmx.demopetclinic.controllers.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controllers.dtos.OwnerDto;
import com.github.syndexmx.demopetclinic.domain.OwnerFields;
import com.github.syndexmx.demopetclinic.domain.Owner;

import java.util.Random;


@TemplatedAnnotation
public class OwnerDtoMapper {

    public static OwnerDto ownerToOwnerDto(Owner owner) {
        final OwnerDto ownerDto = OwnerDto.builder()
                .id(owner.getId())
                .ownerFieldContent(owner.getOwnerFields().toString())
                .build();
        return ownerDto;
    }

    public static Owner ownerDtoToOwner(OwnerDto ownerDto) {
        Owner owner = Owner.builder()
                .id(ownerDto.getId())
                .ownerFields(OwnerFields.valueOf(ownerDto.getOwnerFieldContent()))
                .build();
        return owner;
    }

    public static Owner ownerDtoNoIdToOwner(OwnerDto ownerDto) {
        Random random = new Random();
        Owner owner = Owner.builder()
                .id(random.nextLong())
                .ownerFields(OwnerFields.valueOf(ownerDto.getOwnerFieldContent()))
                .build();
        return owner;
    }

}

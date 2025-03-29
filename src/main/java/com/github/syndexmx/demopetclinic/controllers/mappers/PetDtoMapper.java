package com.github.syndexmx.demopetclinic.controllers.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controllers.dtos.PetDto;
import com.github.syndexmx.demopetclinic.domain.PetFields;
import com.github.syndexmx.demopetclinic.domain.Pet;

import java.util.Random;


@TemplatedAnnotation
public class PetDtoMapper {

    public static PetDto petToPetDto(Pet pet) {
        final PetDto petDto = PetDto.builder()
                .id(pet.getId().toString())
                .petFieldContent(pet.getPetFields().toString())
                .build();
        return petDto;
    }

    public static Pet petDtoToPet(PetDto petDto) {
        Pet pet = Pet.builder()
                .id(Long.parseLong(petDto.getId()))
                .petFields(PetFields.valueOf(petDto.getPetFieldContent()))
                .build();
        return pet;
    }

    public static Pet petDtoNoIdToPet(PetDto petDto) {
        Random random = new Random();
        Pet pet = Pet.builder()
                .id(random.nextLong())
                .petFields(PetFields.valueOf(petDto.getPetFieldContent()))
                .build();
        return pet;
    }

}

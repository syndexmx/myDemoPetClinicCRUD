package com.github.syndexmx.demopetclinic.controller.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controller.dtos.PetDto;
import com.github.syndexmx.demopetclinic.domain.PetSpecies;
import com.github.syndexmx.demopetclinic.domain.Pet;

import java.time.LocalDate;
import java.util.Random;


@TemplatedAnnotation
public class PetDtoMapper {

    public static PetDto petToPetDto(Pet pet) {
        final PetDto petDto = PetDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .breed(pet.getBreed())
                .birthDate(pet.getBirthDate().toString())
                .weight(pet.getWeight())
                .colour(pet.getColour())
                .petSpecies(pet.getPetSpecies().toString())
                .build();
        return petDto;
    }

    public static Pet petDtoToPet(PetDto petDto) {
        Pet pet = Pet.builder()
                .id(petDto.getId())
                .name(petDto.getName())
                .breed(petDto.getBreed())
                .birthDate(LocalDate.parse(petDto.getBirthDate()))
                .weight(petDto.getWeight())
                .colour(petDto.getColour())
                .petSpecies(PetSpecies.valueOf(petDto.getPetSpecies()))
                .build();
        return pet;
    }

    public static Pet petDtoNoIdToPet(PetDto petDto) {
        Random random = new Random();
        Pet pet = Pet.builder()
                .id(random.nextLong())
                .name(petDto.getName())
                .breed(petDto.getBreed())
                .birthDate(LocalDate.parse(petDto.getBirthDate()))
                .weight(petDto.getWeight())
                .colour(petDto.getColour())
                .petSpecies(PetSpecies.valueOf(petDto.getPetSpecies()))
                .build();
        return pet;
    }

}

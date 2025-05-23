package com.github.syndexmx.demopetclinic.controller.mappers;

import com.github.syndexmx.demopetclinic.controller.dtos.PetDto;
import com.github.syndexmx.demopetclinic.domain.Sex;
import com.github.syndexmx.demopetclinic.domain.Species;
import com.github.syndexmx.demopetclinic.domain.Pet;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

@Service
public class PetDtoMapper {

    public PetDto petToPetDto(Pet pet) {
        final PetDto petDto = PetDto.builder()
                .id(pet.getId())
                .ownerId(pet.getOwnerId())
                .name(pet.getName())
                .breed(pet.getBreed())
                .birthDate(pet.getBirthDate().toString())
                .weight(pet.getWeight())
                .colour(pet.getColour())
                .petSpecies(pet.getSpecies().toString())
                .sex(pet.getSex().toString())
                .admissionIdList(pet.getAdmissionIdList().stream().toList())
                .build();
        return petDto;
    }

    public Pet petDtoToPet(PetDto petDto) {
        Pet pet = Pet.builder()
                .id(petDto.getId())
                .ownerId(petDto.getOwnerId())
                .name(petDto.getName())
                .breed(petDto.getBreed())
                .birthDate(LocalDate.parse(petDto.getBirthDate()))
                .weight(petDto.getWeight())
                .colour(petDto.getColour())
                .species(Species.valueOf(petDto.getPetSpecies()))
                .sex(Sex.valueOf(petDto.getSex()))
                .admissionIdList(petDto.getAdmissionIdList().stream().toList())
                .build();
        return pet;
    }

    public Pet petDtoNoIdToPet(PetDto petDto) {
        Random random = new Random();
        Pet pet = Pet.builder()
                .id(random.nextLong())
                .ownerId(petDto.getOwnerId())
                .name(petDto.getName())
                .breed(petDto.getBreed())
                .birthDate(LocalDate.parse(petDto.getBirthDate()))
                .weight(petDto.getWeight())
                .colour(petDto.getColour())
                .species(Species.valueOf(petDto.getPetSpecies()))
                .sex(Sex.valueOf(petDto.getSex()))
                .admissionIdList(new ArrayList<Long>())
                .build();
        return pet;
    }

}

package com.github.syndexmx.demopetclinic.repositories.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.PetSpecies;
import com.github.syndexmx.demopetclinic.domain.Pet;
import com.github.syndexmx.demopetclinic.repositories.entities.PetEntity;

@TemplatedAnnotation
public class PetEntityMapper {

    public static PetEntity petToPetEntity(Pet pet) {
        final PetEntity petEntity = PetEntity.builder()
                .petId(pet.getId())
                .breed(pet.getBreed())
                .birthDate(pet.getBirthDate())
                .name(pet.getName())
                .weight(pet.getWeight())
                .colour(pet.getColour())
                .petSpecies(pet.getPetSpecies().toString())
                .build();
        return petEntity;
    }

    public static Pet petEntityToPet(PetEntity petEntity) {
        Pet pet = Pet.builder()
                .id(petEntity.getPetId())
                .breed(petEntity.getBreed())
                .birthDate(petEntity.getBirthDate())
                .name(petEntity.getName())
                .weight(petEntity.getWeight())
                .colour(petEntity.getColour())
                .petSpecies(PetSpecies.valueOf(petEntity.getPetSpecies()))
                .build();
        return pet;
    }



}

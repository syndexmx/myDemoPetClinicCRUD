package com.github.syndexmx.demopetclinic.repositories.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.PetFields;
import com.github.syndexmx.demopetclinic.domain.Pet;
import com.github.syndexmx.demopetclinic.repositories.entities.PetEntity;

@TemplatedAnnotation
public class PetEntityMapper {

    public static PetEntity petToPetEntity(Pet pet) {
        final PetEntity petEntity = PetEntity.builder()
                .petId(pet.getId())
                .petFieldContent(pet.getPetFields().toString())
                .build();
        return petEntity;
    }

    public static Pet petEntityToPet(PetEntity petEntity) {
        Pet pet = Pet.builder()
                .id(petEntity.getPetId())
                .petFields(PetFields.valueOf(petEntity.getPetFieldContent()))
                .build();
        return pet;
    }



}

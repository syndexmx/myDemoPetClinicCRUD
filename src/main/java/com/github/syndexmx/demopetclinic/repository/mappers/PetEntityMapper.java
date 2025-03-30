package com.github.syndexmx.demopetclinic.repository.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.PetSpecies;
import com.github.syndexmx.demopetclinic.domain.Pet;
import com.github.syndexmx.demopetclinic.repository.entities.PetEntity;
import com.github.syndexmx.demopetclinic.services.OwnerService;
import com.github.syndexmx.demopetclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@TemplatedAnnotation
public class PetEntityMapper {

    @Autowired
    @Lazy
    OwnerService ownerService;

    @Autowired
    @Lazy
    OwnerEntityMapper ownerEntityMapper;

    public PetEntity petToPetEntity(Pet pet) {
        final PetEntity petEntity = PetEntity.builder()
                .petId(pet.getId())
                .owner(ownerEntityMapper.ownerToOwnerEntity(
                        ownerService.findById(pet.getOwnerId()).orElseThrow()))
                .breed(pet.getBreed())
                .birthDate(pet.getBirthDate())
                .name(pet.getName())
                .weight(pet.getWeight())
                .colour(pet.getColour())
                .petSpecies(pet.getPetSpecies().toString())
                .build();
        return petEntity;
    }

    public Pet petEntityToPet(PetEntity petEntity) {
        Pet pet = Pet.builder()
                .id(petEntity.getPetId())
                .ownerId(petEntity.getOwner().getOwnerId())
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

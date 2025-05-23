package com.github.syndexmx.demopetclinic.repository.mappers;

import com.github.syndexmx.demopetclinic.domain.*;
import com.github.syndexmx.demopetclinic.repository.entities.PetEntity;
import com.github.syndexmx.demopetclinic.services.AdmissionService;
import com.github.syndexmx.demopetclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class PetEntityMapper {

    @Autowired
    @Lazy
    AdmissionService admissionService;

    @Autowired
    @Lazy
    AdmissionEntityMapper admissionEntityMapper;

    public PetEntity petToPetEntity(Pet pet) {
        final PetEntity petEntity = PetEntity.builder()
                .id(pet.getId())
                .ownerId(pet.getOwnerId())
                .breed(pet.getBreed())
                .birthDate(pet.getBirthDate())
                .name(pet.getName())
                .weight(pet.getWeight())
                .colour(pet.getColour())
                .petSpecies(pet.getSpecies().toString())
                .sex(pet.getSex().toString())
                .admissionList(pet.getAdmissionIdList().stream()
                        .map(admissionId -> {
                            Admission admission = admissionService.findById(admissionId).orElseThrow();
                            return admissionEntityMapper.admissionToAdmissionEntity(admission);
                        })
                        .toList())
                .build();
        return petEntity;
    }

    public Pet petEntityToPet(PetEntity petEntity) {
        Pet pet = Pet.builder()
                .id(petEntity.getId())
                .ownerId(petEntity.getOwnerId())
                .breed(petEntity.getBreed())
                .birthDate(petEntity.getBirthDate())
                .name(petEntity.getName())
                .weight(petEntity.getWeight())
                .colour(petEntity.getColour())
                .species(Species.valueOf(petEntity.getPetSpecies()))
                .sex(Sex.valueOf(petEntity.getSex()))
                .admissionIdList(petEntity.getAdmissionList().stream()
                        .map(admissionEntity -> admissionEntity.getId())
                        .toList())
                .build();
        return pet;
    }



}

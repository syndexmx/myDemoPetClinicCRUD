package com.github.syndexmx.demopetclinic.controller.mappers;

import com.github.syndexmx.demopetclinic.controller.dtos.PetReturnDto;
import com.github.syndexmx.demopetclinic.domain.Pet;
import com.github.syndexmx.demopetclinic.services.AdmissionService;
import com.github.syndexmx.demopetclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class PetReturnDtoMapper {

    @Autowired
    @Lazy
    AdmissionService admissionService;

    @Autowired
    @Lazy
    AdmissionDtoMapper admissionDtoMapper;

    public PetReturnDto petToPetDto(Pet pet) {
        final PetReturnDto petDto = PetReturnDto.builder()
                .id(pet.getId())
                .ownerId(pet.getOwnerId())
                .name(pet.getName())
                .breed(pet.getBreed())
                .birthDate(pet.getBirthDate().toString())
                .weight(pet.getWeight())
                .colour(pet.getColour())
                .petSpecies(pet.getSpecies().toString())
                .sex(pet.getSex().toString())
                .admissionList(pet.getAdmissionIdList().stream()
                        .map(admissionId -> admissionDtoMapper.admissionToAdmissionDto(admissionService
                                .findById(admissionId).orElseThrow()))
                        .toList())
                .build();
        return petDto;
    }
}

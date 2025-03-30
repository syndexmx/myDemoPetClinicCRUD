package com.github.syndexmx.demopetclinic.controller.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controller.dtos.OwnerDto;
import com.github.syndexmx.demopetclinic.domain.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@TemplatedAnnotation
public class OwnerDtoMapper {

    @Autowired
    private final PetDtoMapper petDtoMapper;

    public OwnerDtoMapper(PetDtoMapper petDtoMapper) {
        this.petDtoMapper = petDtoMapper;
    }

    public OwnerDto ownerToOwnerDto(Owner owner) {
        final OwnerDto ownerDto = OwnerDto.builder()
                .id(owner.getId())
                .name(owner.getName())
                .phone(owner.getPhone())
                .address(owner.getAddress())
                //.petList(owner.getPetIdList().stream()
                //        .map(pet -> petToPetDto(pet))
                //        .toList())
                .build();
        return ownerDto;
    }

    public Owner ownerDtoToOwner(OwnerDto ownerDto) {
        Owner owner = Owner.builder()
                .id(ownerDto.getId())
                .name(ownerDto.getName())
                .phone(ownerDto.getPhone())
                .address(ownerDto.getAddress())
                //.petIdList(ownerDto.getPetList().stream()
                //        .map(petId -> petId)
                //        .toList())
                .build();
        return owner;
    }

    public Owner ownerDtoNoIdToOwner(OwnerDto ownerDto) {
        Random random = new Random();
        Owner owner = Owner.builder()
                .id(random.nextLong())
                .name(ownerDto.getName())
                .phone(ownerDto.getPhone())
                .address(ownerDto.getAddress())
                //.petIdList(new ArrayList<>())
                .build();
        return owner;
    }

}

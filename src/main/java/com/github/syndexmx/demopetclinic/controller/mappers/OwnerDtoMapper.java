package com.github.syndexmx.demopetclinic.controller.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controller.dtos.OwnerDto;
import com.github.syndexmx.demopetclinic.domain.Owner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
@TemplatedAnnotation
public class OwnerDtoMapper {

    public OwnerDto ownerToOwnerDto(Owner owner) {
        final OwnerDto ownerDto = OwnerDto.builder()
                .id(owner.getId())
                .name(owner.getName())
                .phone(owner.getPhone())
                .address(owner.getAddress())
                .petList(owner.getPetIdList().stream().toList())
                .build();
        return ownerDto;
    }

    public Owner ownerDtoToOwner(OwnerDto ownerDto) {
        Owner owner = Owner.builder()
                .id(ownerDto.getId())
                .name(ownerDto.getName())
                .phone(ownerDto.getPhone())
                .address(ownerDto.getAddress())
                .petIdList(ownerDto.getPetList().stream().toList())
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
                .petIdList(new ArrayList<Long>())
                .build();
        return owner;
    }

}

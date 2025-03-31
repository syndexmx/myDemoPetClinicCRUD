package com.github.syndexmx.demopetclinic.controller.mappers;

import com.github.syndexmx.demopetclinic.controller.dtos.OwnerDto;
import com.github.syndexmx.demopetclinic.controller.dtos.OwnerReturnDto;
import com.github.syndexmx.demopetclinic.domain.Owner;
import com.github.syndexmx.demopetclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class OwnerReturnDtoMapper {

    @Autowired
    @Lazy
    PetService petService;

    @Autowired
    @Lazy
    PetDtoMapper petDtoMapper;

    public OwnerReturnDto ownerToOwnerDto(Owner owner) {
        final OwnerReturnDto ownerDto = OwnerReturnDto.builder()
                .id(owner.getId())
                .name(owner.getName())
                .phone(owner.getPhone())
                .addressId(owner.getAddressId())
                .petList(owner.getPetIdList().stream()
                        .map(petId -> petDtoMapper.petToPetDto(petService
                                        .findById(petId).orElseThrow()))
                        .toList())
                .build();
        return ownerDto;
    }
}

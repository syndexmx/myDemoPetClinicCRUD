package com.github.syndexmx.demopetclinic.repository.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Owner;
import com.github.syndexmx.demopetclinic.domain.Pet;
import com.github.syndexmx.demopetclinic.repository.entities.OwnerEntity;
import com.github.syndexmx.demopetclinic.services.AddressService;
import com.github.syndexmx.demopetclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@TemplatedAnnotation
public class OwnerEntityMapper {

    @Autowired
    @Lazy
    PetService petService;

    @Autowired
    @Lazy
    PetEntityMapper petEntityMapper;

    public OwnerEntity ownerToOwnerEntity(Owner owner) {

        final OwnerEntity ownerEntity = OwnerEntity.builder()
                .id(owner.getId())
                .name(owner.getName())
                .phone(owner.getPhone())
                .addressId(owner.getAddressId())
                .petList(owner.getPetIdList().stream()
                        .map(petId -> {
                            Pet pet = petService.findById(petId).orElseThrow();
                            return petEntityMapper.petToPetEntity(pet);
                        })
                        .toList())
                .build();
        return ownerEntity;
    }

    public Owner ownerEntityToOwner(OwnerEntity ownerEntity) {
        Owner owner = Owner.builder()
                .id(ownerEntity.getId())
                .name(ownerEntity.getName())
                .phone(ownerEntity.getPhone())
                .addressId(ownerEntity.getAddressId())
                .petIdList(ownerEntity.getPetList().stream()
                        .map(petEntity -> petEntity.getId())
                        .toList())
                .build();
        return owner;
    }

}

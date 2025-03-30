package com.github.syndexmx.demopetclinic.repository.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Owner;
import com.github.syndexmx.demopetclinic.repository.entities.OwnerEntity;
import com.github.syndexmx.demopetclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@TemplatedAnnotation
public class OwnerEntityMapper {

    public OwnerEntity ownerToOwnerEntity(Owner owner) {

        final OwnerEntity ownerEntity = OwnerEntity.builder()
                .ownerId(owner.getId())
                .name(owner.getName())
                .phone(owner.getPhone())
                .address(owner.getAddress())
                //.petList(owner.getPetIdList().stream()
//                        .toList())
                .build();
        return ownerEntity;
    }

    public Owner ownerEntityToOwner(OwnerEntity ownerEntity) {
        Owner owner = Owner.builder()
                .id(ownerEntity.getOwnerId())
                .name(ownerEntity.getName())
                .phone(ownerEntity.getPhone())
                .address(ownerEntity.getAddress())
                //.petIdList(ownerEntity.getPetList().stream()
                 //       .map(petEntity -> petEntityToPet(petEntity))
                 //       .toList())
                .build();
        return owner;
    }



}

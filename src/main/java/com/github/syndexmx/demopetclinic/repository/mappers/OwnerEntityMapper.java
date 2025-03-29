package com.github.syndexmx.demopetclinic.repository.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.OwnerFields;
import com.github.syndexmx.demopetclinic.domain.Owner;
import com.github.syndexmx.demopetclinic.repository.entities.OwnerEntity;

@TemplatedAnnotation
public class OwnerEntityMapper {

    public static OwnerEntity ownerToOwnerEntity(Owner owner) {
        final OwnerEntity ownerEntity = OwnerEntity.builder()
                .ownerId(owner.getId())
                .name(owner.getName())
                .phone(owner.getPhone())
                .address(owner.getAddress())
                .ownerFieldContent(owner.getOwnerFields().toString())
                .build();
        return ownerEntity;
    }

    public static Owner ownerEntityToOwner(OwnerEntity ownerEntity) {
        Owner owner = Owner.builder()
                .id(ownerEntity.getOwnerId())
                .name(ownerEntity.getName())
                .phone(ownerEntity.getPhone())
                .address(ownerEntity.getAddress())
                .ownerFields(OwnerFields.valueOf(ownerEntity.getOwnerFieldContent()))
                .build();
        return owner;
    }



}

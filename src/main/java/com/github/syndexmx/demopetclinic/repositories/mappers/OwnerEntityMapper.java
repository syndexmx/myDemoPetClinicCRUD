package com.github.syndexmx.demopetclinic.repositories.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.OwnerFields;
import com.github.syndexmx.demopetclinic.domain.Owner;
import com.github.syndexmx.demopetclinic.repositories.entities.OwnerEntity;

@TemplatedAnnotation
public class OwnerEntityMapper {

    public static OwnerEntity ownerToOwnerEntity(Owner owner) {
        final OwnerEntity ownerEntity = OwnerEntity.builder()
                .ownerId(owner.getId())
                .ownerFieldContent(owner.getOwnerFields().toString())
                .build();
        return ownerEntity;
    }

    public static Owner ownerEntityToOwner(OwnerEntity ownerEntity) {
        Owner owner = Owner.builder()
                .id(ownerEntity.getOwnerId())
                .ownerFields(OwnerFields.valueOf(ownerEntity.getOwnerFieldContent()))
                .build();
        return owner;
    }



}

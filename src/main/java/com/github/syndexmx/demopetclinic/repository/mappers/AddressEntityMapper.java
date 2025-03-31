package com.github.syndexmx.demopetclinic.repository.mappers;

import com.github.syndexmx.demopetclinic.domain.Address;
import com.github.syndexmx.demopetclinic.domain.Owner;
import com.github.syndexmx.demopetclinic.repository.entities.AddressEntity;
import com.github.syndexmx.demopetclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class AddressEntityMapper {

    @Autowired
    @Lazy
    OwnerService ownerService;

    @Autowired
    @Lazy
    OwnerEntityMapper ownerEntityMapper;


    public AddressEntity addressToAddressEntity(Address address) {
        final AddressEntity addressEntity = AddressEntity.builder()
                .id(address.getId())
                .region(address.getRegion())
                .city(address.getCity())
                .house(address.getHouse())
                .street(address.getStreet())
                .building(address.getBuilding())
                .flat(address.getFlat())
                .ownerList(address.getOwnerIdList().stream()
                        .map(ownerId -> {
                            Owner owner = ownerService.findById(ownerId).orElseThrow();
                            return ownerEntityMapper.ownerToOwnerEntity(owner);
                        })
                        .toList())
                .build();
        return addressEntity;
    }

    public Address addressEntityToAddress(AddressEntity addressEntity) {
        Address address = Address.builder()
                .id(addressEntity.getId())
                .region(addressEntity.getRegion())
                .city(addressEntity.getCity())
                .house(addressEntity.getHouse())
                .street(addressEntity.getStreet())
                .building(addressEntity.getBuilding())
                .flat(addressEntity.getFlat())
                .ownerIdList(addressEntity.getOwnerList().stream()
                        .map(ownerEntity -> ownerEntity.getId())
                        .toList())
                .build();
        return address;
    }



}

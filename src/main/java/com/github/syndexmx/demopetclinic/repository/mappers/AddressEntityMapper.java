package com.github.syndexmx.demopetclinic.repository.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Address;
import com.github.syndexmx.demopetclinic.repository.entities.AddressEntity;
import org.springframework.stereotype.Service;

@Service
@TemplatedAnnotation
public class AddressEntityMapper {

    public AddressEntity addressToAddressEntity(Address address) {
        final AddressEntity addressEntity = AddressEntity.builder()
                .addressId(address.getId())
                .region(address.getRegion())
                .city(address.getCity())
                .house(address.getHouse())
                .street(address.getStreet())
                .building(address.getBuilding())
                .flat(address.getFlat())
                .build();
        return addressEntity;
    }

    public Address addressEntityToAddress(AddressEntity addressEntity) {
        Address address = Address.builder()
                .id(addressEntity.getAddressId())
                .build();
        return address;
    }



}

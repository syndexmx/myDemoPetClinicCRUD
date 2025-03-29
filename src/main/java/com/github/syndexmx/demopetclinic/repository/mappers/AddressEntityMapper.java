package com.github.syndexmx.demopetclinic.repository.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.AddressFields;
import com.github.syndexmx.demopetclinic.domain.Address;
import com.github.syndexmx.demopetclinic.repository.entities.AddressEntity;

@TemplatedAnnotation
public class AddressEntityMapper {

    public static AddressEntity addressToAddressEntity(Address address) {
        final AddressEntity addressEntity = AddressEntity.builder()
                .addressId(address.getId())
                .addressFieldContent(address.getAddressFields().toString())
                .build();
        return addressEntity;
    }

    public static Address addressEntityToAddress(AddressEntity addressEntity) {
        Address address = Address.builder()
                .id(addressEntity.getAddressId())
                .addressFields(AddressFields.valueOf(addressEntity.getAddressFieldContent()))
                .build();
        return address;
    }



}

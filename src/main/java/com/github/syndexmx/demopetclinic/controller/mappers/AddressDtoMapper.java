package com.github.syndexmx.demopetclinic.controller.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controller.dtos.AddressDto;
import com.github.syndexmx.demopetclinic.domain.AddressFields;
import com.github.syndexmx.demopetclinic.domain.Address;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@TemplatedAnnotation
public class AddressDtoMapper {

    public AddressDto addressToAddressDto(Address address) {
        final AddressDto addressDto = AddressDto.builder()
                .id(address.getId())
                .region(address.getRegion())
                .city(address.getCity())
                .house(address.getHouse())
                .street(address.getStreet())
                .building(address.getBuilding())
                .flat(address.getFlat())
                .addressFieldContent(address.getAddressFields().toString())
                .build();
        return addressDto;
    }

    public Address addressDtoToAddress(AddressDto addressDto) {
        Address address = Address.builder()
                .id(addressDto.getId())
                .region(addressDto.getRegion())
                .city(addressDto.getCity())
                .house(addressDto.getHouse())
                .street(addressDto.getStreet())
                .building(addressDto.getBuilding())
                .flat(addressDto.getFlat())
                .addressFields(AddressFields.valueOf(addressDto.getAddressFieldContent()))
                .build();
        return address;
    }

    public Address addressDtoNoIdToAddress(AddressDto addressDto) {
        Random random = new Random();
        Address address = Address.builder()
                .id(random.nextLong())
                .region(addressDto.getRegion())
                .city(addressDto.getCity())
                .house(addressDto.getHouse())
                .street(addressDto.getStreet())
                .building(addressDto.getBuilding())
                .flat(addressDto.getFlat())
                .addressFields(AddressFields.valueOf(addressDto.getAddressFieldContent()))
                .build();
        return address;
    }

}

package com.github.syndexmx.demopetclinic.controllers.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controllers.dtos.AddressDto;
import com.github.syndexmx.demopetclinic.domain.AddressFields;
import com.github.syndexmx.demopetclinic.domain.Address;

import java.util.Random;


@TemplatedAnnotation
public class AddressDtoMapper {

    public static AddressDto addressToAddressDto(Address address) {
        final AddressDto addressDto = AddressDto.builder()
                .id(address.getId())
                .addressFieldContent(address.getAddressFields().toString())
                .build();
        return addressDto;
    }

    public static Address addressDtoToAddress(AddressDto addressDto) {
        Address address = Address.builder()
                .id(addressDto.getId())
                .addressFields(AddressFields.valueOf(addressDto.getAddressFieldContent()))
                .build();
        return address;
    }

    public static Address addressDtoNoIdToAddress(AddressDto addressDto) {
        Random random = new Random();
        Address address = Address.builder()
                .id(random.nextLong())
                .addressFields(AddressFields.valueOf(addressDto.getAddressFieldContent()))
                .build();
        return address;
    }

}

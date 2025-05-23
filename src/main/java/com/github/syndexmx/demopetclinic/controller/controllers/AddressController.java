package com.github.syndexmx.demopetclinic.controller.controllers;

import com.github.syndexmx.demopetclinic.controller.mappers.AddressDtoMapper;
import com.github.syndexmx.demopetclinic.domain.Address;
import com.github.syndexmx.demopetclinic.controller.dtos.AddressDto;
import com.github.syndexmx.demopetclinic.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Address", description = "API Адрес")
@Slf4j
public class AddressController {

    private final String ROOT_API_PATH = "/api/v0/addresses";

    private final AddressService addressService;
    private final AddressDtoMapper addressDtoMapper;

    private AddressController(@Autowired AddressService addressService,
                              @Autowired AddressDtoMapper addressDtoMapper) {
        this.addressService = addressService;
        this.addressDtoMapper = addressDtoMapper;
    }

    @PostMapping(ROOT_API_PATH)
    @Operation(summary = "Адрес:добавить",
            description = "Создание нового объекта Адрес. id присваивается системой")
    public ResponseEntity<AddressDto> create(@RequestBody final AddressDto addressDto) {
        log.info("POST " + ROOT_API_PATH + "\n" + addressDto);
        final Address address = addressDtoMapper.addressDtoNoIdToAddress(addressDto);
        final ResponseEntity<AddressDto> responseEntity = new ResponseEntity<> (
                addressDtoMapper.addressToAddressDto(addressService.create(address)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH + "/{addressId}")
    @Operation(summary = "Адрес:получить по id",
            description = "Получить существующий объект Адрес")
    public ResponseEntity<AddressDto> retrieve(@PathVariable Long addressId) {
        final Optional<Address> foundAddress = addressService.findById(addressId);
        if (foundAddress.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            final AddressDto addressDto = addressDtoMapper.addressToAddressDto(foundAddress.get());
            return new ResponseEntity<>(addressDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    @Operation(summary = "Адрес:получить весь список",
            description = "Получить все существующие в базе объекты Адрес")
    public ResponseEntity<List<AddressDto>> retrieveAll() {
        final List<Address> listFound = addressService.listAll();
        final List<AddressDto> listFoundDtos = listFound.stream()
                .map(address -> addressDtoMapper.addressToAddressDto(address)).toList();
        final ResponseEntity<List<AddressDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH + "/{addressId}")
    @Operation(summary = "Адрес:обновить объект по id",
            description = "Обновить существующий в базе объект Адрес")
    public ResponseEntity<AddressDto> update(@RequestBody final AddressDto addressDto) {
        log.info("PUT " + ROOT_API_PATH + " \n" + addressDto);
        final Address address = addressDtoMapper.addressDtoToAddress(addressDto);
        if (!addressService.isPresent(address)) {
            final ResponseEntity<AddressDto> responseEntity = new ResponseEntity<> (
                    addressDtoMapper.addressToAddressDto(addressService.save(address)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<AddressDto> responseEntity = new ResponseEntity<> (
                addressDtoMapper.addressToAddressDto(addressService.save(address)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH + "/{addressId}")
    @Operation(summary = "Адрес:удалить объект по id",
            description = "Удалить существующий в базе объект Адрес")
    public ResponseEntity deleteById(@PathVariable Long addressId) {
        log.info("DELETE " + ROOT_API_PATH + " \n" + addressId);
        addressService.deleteById(addressId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

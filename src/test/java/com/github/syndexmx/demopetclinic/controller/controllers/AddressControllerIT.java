package com.github.syndexmx.demopetclinic.controller.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controller.mappers.AddressDtoMapper;
import com.github.syndexmx.demopetclinic.domain.Address;
import com.github.syndexmx.demopetclinic.domain.AddressTestSupplierKit;
import com.github.syndexmx.demopetclinic.controller.dtos.AddressDto;
import com.github.syndexmx.demopetclinic.services.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

@TemplatedAnnotation
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AddressControllerIT {

    private final String ROOT_API_PATH = "/api/v0/addresss";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressDtoMapper addressDtoMapper;

    // TODO : patch tests

    @Test
    public void testThatAddressIsCreated() throws Exception {
        Address address = AddressTestSupplierKit.getTestAddress();
        final AddressDto addressDto = addressDtoMapper.addressToAddressDto(address);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String addressJson = objectMapper.writeValueAsString(addressDto);
        mockMvc.perform(MockMvcRequestBuilders.post(ROOT_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(addressJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        List<Address> savedAddressList = addressService.listAll();
        assertEquals(1, savedAddressList.size());
        Address savedAddress = savedAddressList.get(0);
        final Long id = savedAddress.getId();
        address.setId(id);
        assertEquals(address, savedAddress);
    }

    @Test
    public void testThatAddressIsUpdated() throws Exception {
        final Address address = AddressTestSupplierKit.getTestAddress();
        Address savedAddress = addressService.create(address);
        final Long id = savedAddress.getId();
        Address modifiedAddress = AddressTestSupplierKit.getModifiedTestAddress();
        modifiedAddress.setId(id);
        final AddressDto modifiedAddressDto = addressDtoMapper.addressToAddressDto(modifiedAddress);
        final ObjectMapper modifiedObjectMapper = new ObjectMapper();
        final String modifiedAddressJson = modifiedObjectMapper.writeValueAsString(modifiedAddressDto);
        mockMvc.perform(MockMvcRequestBuilders.put(ROOT_API_PATH + "/" + id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifiedAddressJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(modifiedAddressJson));
    }

    @Test
    public void testThatRetrieveReturnsNotFoundWhenAbsent() throws Exception {
        final Address address = AddressTestSupplierKit.getTestNonExistentAddress();
        final Long id = address.getId();
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveReturnsAddressWhenExists() throws Exception {
        final Address address = AddressTestSupplierKit.getTestAddress();
        final Address addressSaved = addressService.create(address);
        final Long id = addressSaved.getId();
        final AddressDto addressDto = addressDtoMapper.addressToAddressDto(addressSaved);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String addressJson = objectMapper.writeValueAsString(addressDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(addressJson));
    }

    @Test
    public void testThatRetrieveAllReturnsEmptyWhenAbsent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testThatRetrieveAllReturnsListWhenExist() throws Exception {
        final Address address = AddressTestSupplierKit.getTestAddress();
        final Address addressSaved = addressService.create(address);
        final AddressDto addressDto = addressDtoMapper.addressToAddressDto(addressSaved);
        final List<AddressDto> listAddressDto = new ArrayList<>(List.of(addressDto));
        final ObjectMapper objectMapper = new ObjectMapper();
        final String addressListJson = objectMapper.writeValueAsString(listAddressDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(addressListJson));

    }

    @Test
    public void testThatDeleteAddressByIdReturnsHttp204WhenAbsent() throws Exception {
        final Address address = AddressTestSupplierKit.getTestAddress();
        final Long id = address.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteAddressByIdDeletesAddress() throws Exception {
        final Address address = AddressTestSupplierKit.getTestAddress();
        final Address savedAddress = addressService.save(address);
        final Long id = savedAddress.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

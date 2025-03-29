package com.github.syndexmx.demopetclinic.controller.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Owner;
import com.github.syndexmx.demopetclinic.domain.OwnerTestSupplierKit;
import com.github.syndexmx.demopetclinic.controller.dtos.OwnerDto;
import com.github.syndexmx.demopetclinic.services.OwnerService;
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


import static com.github.syndexmx.demopetclinic.controller.mappers.OwnerDtoMapper.ownerToOwnerDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TemplatedAnnotation
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OwnerControllerIT {

    private final String ROOT_API_PATH = "/api/v0/owners";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OwnerService ownerService;

    @Test
    public void testThatOwnerIsCreated() throws Exception {
        Owner owner = OwnerTestSupplierKit.getTestOwner();
        final OwnerDto ownerDto = ownerToOwnerDto(owner);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String ownerJson = objectMapper.writeValueAsString(ownerDto);
        mockMvc.perform(MockMvcRequestBuilders.post(ROOT_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(ownerJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        List<Owner> savedOwnerList = ownerService.listAll();
        assertEquals(1, savedOwnerList.size());
        Owner savedOwner = savedOwnerList.get(0);
        final Long id = savedOwner.getId();
        owner.setId(id);
        assertEquals(owner, savedOwner);
    }

    @Test
    public void testThatOwnerIsUpdated() throws Exception {
        final Owner owner = OwnerTestSupplierKit.getTestOwner();
        Owner savedOwner = ownerService.create(owner);
        final Long id = savedOwner.getId();
        Owner modifiedOwner = OwnerTestSupplierKit.getModifiedTestOwner();
        modifiedOwner.setId(id);
        final OwnerDto modifiedOwnerDto = ownerToOwnerDto(modifiedOwner);
        final ObjectMapper modifiedObjectMapper = new ObjectMapper();
        final String modifiedOwnerJson = modifiedObjectMapper.writeValueAsString(modifiedOwnerDto);
        mockMvc.perform(MockMvcRequestBuilders.put(ROOT_API_PATH + "/" + id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifiedOwnerJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(modifiedOwnerJson));
    }

    @Test
    public void testThatRetrieveReturnsNotFoundWhenAbsent() throws Exception {
        final Owner owner = OwnerTestSupplierKit.getTestNonExistentOwner();
        final Long id = owner.getId();
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveReturnsOwnerWhenExists() throws Exception {
        final Owner owner = OwnerTestSupplierKit.getTestOwner();
        final Owner ownerSaved = ownerService.create(owner);
        final Long id = ownerSaved.getId();
        final OwnerDto ownerDto = ownerToOwnerDto(ownerSaved);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String ownerJson = objectMapper.writeValueAsString(ownerDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(ownerJson));
    }

    @Test
    public void testThatRetrieveAllReturnsEmptyWhenAbsent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testThatRetrieveAllReturnsListWhenExist() throws Exception {
        final Owner owner = OwnerTestSupplierKit.getTestOwner();
        final Owner ownerSaved = ownerService.create(owner);
        final OwnerDto ownerDto = ownerToOwnerDto(ownerSaved);
        final List<OwnerDto> listOwnerDto = new ArrayList<>(List.of(ownerDto));
        final ObjectMapper objectMapper = new ObjectMapper();
        final String ownerListJson = objectMapper.writeValueAsString(listOwnerDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(ownerListJson));

    }

    @Test
    public void testThatDeleteOwnerByIdReturnsHttp204WhenAbsent() throws Exception {
        final Owner owner = OwnerTestSupplierKit.getTestOwner();
        final Long id = owner.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteOwnerByIdDeletesOwner() throws Exception {
        final Owner owner = OwnerTestSupplierKit.getTestOwner();
        final Owner savedOwner = ownerService.save(owner);
        final Long id = savedOwner.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

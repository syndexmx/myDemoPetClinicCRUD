package com.github.syndexmx.demopetclinic.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Pet;
import com.github.syndexmx.demopetclinic.domain.PetTestSupplierKit;
import com.github.syndexmx.demopetclinic.controllers.dtos.PetDto;
import com.github.syndexmx.demopetclinic.services.PetService;
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


import static com.github.syndexmx.demopetclinic.controllers.mappers.PetDtoMapper.petToPetDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TemplatedAnnotation
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PetControllerIT {

    private final String ROOT_API_PATH = "/api/v0/pets";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PetService petService;

    @Test
    public void testThatPetIsCreated() throws Exception {
        Pet pet = PetTestSupplierKit.getTestPet();
        final PetDto petDto = petToPetDto(pet);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String petJson = objectMapper.writeValueAsString(petDto);
        mockMvc.perform(MockMvcRequestBuilders.post(ROOT_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(petJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        List<Pet> savedPetList = petService.listAll();
        assertEquals(1, savedPetList.size());
        Pet savedPet = savedPetList.get(0);
        final Long id = savedPet.getId();
        pet.setId(id);
        assertEquals(pet, savedPet);
    }

    @Test
    public void testThatPetIsUpdated() throws Exception {
        final Pet pet = PetTestSupplierKit.getTestPet();
        Pet savedPet = petService.create(pet);
        final Long id = savedPet.getId();
        Pet modifiedPet = PetTestSupplierKit.getModifiedTestPet();
        modifiedPet.setId(id);
        final PetDto modifiedPetDto = petToPetDto(modifiedPet);
        final ObjectMapper modifiedObjectMapper = new ObjectMapper();
        final String modifiedPetJson = modifiedObjectMapper.writeValueAsString(modifiedPetDto);
        mockMvc.perform(MockMvcRequestBuilders.put(ROOT_API_PATH + "/" + id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifiedPetJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(modifiedPetJson));
    }

    @Test
    public void testThatRetrieveReturnsNotFoundWhenAbsent() throws Exception {
        final Pet pet = PetTestSupplierKit.getTestNonExistentPet();
        final Long id = pet.getId();
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveReturnsPetWhenExists() throws Exception {
        final Pet pet = PetTestSupplierKit.getTestPet();
        final Pet petSaved = petService.create(pet);
        final Long id = petSaved.getId();
        final PetDto petDto = petToPetDto(petSaved);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String petJson = objectMapper.writeValueAsString(petDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(petJson));
    }

    @Test
    public void testThatRetrieveAllReturnsEmptyWhenAbsent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testThatRetrieveAllReturnsListWhenExist() throws Exception {
        final Pet pet = PetTestSupplierKit.getTestPet();
        final Pet petSaved = petService.create(pet);
        final PetDto petDto = petToPetDto(petSaved);
        final List<PetDto> listPetDto = new ArrayList<>(List.of(petDto));
        final ObjectMapper objectMapper = new ObjectMapper();
        final String petListJson = objectMapper.writeValueAsString(listPetDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(petListJson));

    }

    @Test
    public void testThatDeletePetByIdReturnsHttp204WhenAbsent() throws Exception {
        final Pet pet = PetTestSupplierKit.getTestPet();
        final Long id = pet.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeletePetByIdDeletesPet() throws Exception {
        final Pet pet = PetTestSupplierKit.getTestPet();
        final Pet savedPet = petService.save(pet);
        final Long id = savedPet.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

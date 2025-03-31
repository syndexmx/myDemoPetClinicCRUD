package com.github.syndexmx.demopetclinic.controller.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controller.mappers.TreatmentDtoMapper;
import com.github.syndexmx.demopetclinic.domain.Treatment;
import com.github.syndexmx.demopetclinic.domain.TreatmentTestSupplierKit;
import com.github.syndexmx.demopetclinic.controller.dtos.TreatmentDto;
import com.github.syndexmx.demopetclinic.services.TreatmentService;
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
public class TreatmentControllerIT {

    private final String ROOT_API_PATH = "/api/v0/treatments";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private TreatmentDtoMapper treatmentDtoMapper;

    // TODO : patch tests

    @Test
    public void testThatTreatmentIsCreated() throws Exception {
        Treatment treatment = TreatmentTestSupplierKit.getTestTreatment();
        final TreatmentDto treatmentDto = treatmentDtoMapper.treatmentToTreatmentDto(treatment);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String treatmentJson = objectMapper.writeValueAsString(treatmentDto);
        mockMvc.perform(MockMvcRequestBuilders.post(ROOT_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(treatmentJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        List<Treatment> savedTreatmentList = treatmentService.listAll();
        assertEquals(1, savedTreatmentList.size());
        Treatment savedTreatment = savedTreatmentList.get(0);
        final Long id = savedTreatment.getId();
        treatment.setId(id);
        assertEquals(treatment, savedTreatment);
    }

    @Test
    public void testThatTreatmentIsUpdated() throws Exception {
        final Treatment treatment = TreatmentTestSupplierKit.getTestTreatment();
        Treatment savedTreatment = treatmentService.create(treatment);
        final Long id = savedTreatment.getId();
        Treatment modifiedTreatment = TreatmentTestSupplierKit.getModifiedTestTreatment();
        modifiedTreatment.setId(id);
        final TreatmentDto modifiedTreatmentDto = treatmentDtoMapper.treatmentToTreatmentDto(modifiedTreatment);
        final ObjectMapper modifiedObjectMapper = new ObjectMapper();
        final String modifiedTreatmentJson = modifiedObjectMapper.writeValueAsString(modifiedTreatmentDto);
        mockMvc.perform(MockMvcRequestBuilders.put(ROOT_API_PATH + "/" + id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifiedTreatmentJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(modifiedTreatmentJson));
    }

    @Test
    public void testThatRetrieveReturnsNotFoundWhenAbsent() throws Exception {
        final Treatment treatment = TreatmentTestSupplierKit.getTestNonExistentTreatment();
        final Long id = treatment.getId();
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveReturnsTreatmentWhenExists() throws Exception {
        final Treatment treatment = TreatmentTestSupplierKit.getTestTreatment();
        final Treatment treatmentSaved = treatmentService.create(treatment);
        final Long id = treatmentSaved.getId();
        final TreatmentDto treatmentDto = treatmentDtoMapper.treatmentToTreatmentDto(treatmentSaved);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String treatmentJson = objectMapper.writeValueAsString(treatmentDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(treatmentJson));
    }

    @Test
    public void testThatRetrieveAllReturnsEmptyWhenAbsent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testThatRetrieveAllReturnsListWhenExist() throws Exception {
        final Treatment treatment = TreatmentTestSupplierKit.getTestTreatment();
        final Treatment treatmentSaved = treatmentService.create(treatment);
        final TreatmentDto treatmentDto = treatmentDtoMapper.treatmentToTreatmentDto(treatmentSaved);
        final List<TreatmentDto> listTreatmentDto = new ArrayList<>(List.of(treatmentDto));
        final ObjectMapper objectMapper = new ObjectMapper();
        final String treatmentListJson = objectMapper.writeValueAsString(listTreatmentDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(treatmentListJson));

    }

    @Test
    public void testThatDeleteTreatmentByIdReturnsHttp204WhenAbsent() throws Exception {
        final Treatment treatment = TreatmentTestSupplierKit.getTestTreatment();
        final Long id = treatment.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteTreatmentByIdDeletesTreatment() throws Exception {
        final Treatment treatment = TreatmentTestSupplierKit.getTestTreatment();
        final Treatment savedTreatment = treatmentService.save(treatment);
        final Long id = savedTreatment.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

package com.github.syndexmx.demopetclinic.controller.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.syndexmx.demopetclinic.controller.mappers.AdmissionDtoMapper;
import com.github.syndexmx.demopetclinic.domain.Admission;
import com.github.syndexmx.demopetclinic.domain.AdmissionTestSupplierKit;
import com.github.syndexmx.demopetclinic.controller.dtos.AdmissionDto;
import com.github.syndexmx.demopetclinic.services.AdmissionService;
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

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdmissionControllerIT {

    private final String ROOT_API_PATH = "/api/v0/admissions";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdmissionService admissionService;

    @Autowired
    private AdmissionDtoMapper admissionDtoMapper;

    // TODO : patch tests

    @Test
    public void testThatAdmissionIsCreated() throws Exception {
        Admission admission = AdmissionTestSupplierKit.getTestAdmission();
        final AdmissionDto admissionDto = admissionDtoMapper.admissionToAdmissionDto(admission);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String admissionJson = objectMapper.writeValueAsString(admissionDto);
        mockMvc.perform(MockMvcRequestBuilders.post(ROOT_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(admissionJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        List<Admission> savedAdmissionList = admissionService.listAll();
        assertEquals(1, savedAdmissionList.size());
        Admission savedAdmission = savedAdmissionList.get(0);
        final Long id = savedAdmission.getId();
        admission.setId(id);
        assertEquals(admission, savedAdmission);
    }

    @Test
    public void testThatAdmissionIsUpdated() throws Exception {
        final Admission admission = AdmissionTestSupplierKit.getTestAdmission();
        Admission savedAdmission = admissionService.create(admission);
        final Long id = savedAdmission.getId();
        Admission modifiedAdmission = AdmissionTestSupplierKit.getModifiedTestAdmission();
        modifiedAdmission.setId(id);
        final AdmissionDto modifiedAdmissionDto = admissionDtoMapper.admissionToAdmissionDto(modifiedAdmission);
        final ObjectMapper modifiedObjectMapper = new ObjectMapper();
        final String modifiedAdmissionJson = modifiedObjectMapper.writeValueAsString(modifiedAdmissionDto);
        mockMvc.perform(MockMvcRequestBuilders.put(ROOT_API_PATH + "/" + id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifiedAdmissionJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(modifiedAdmissionJson));
    }

    @Test
    public void testThatRetrieveReturnsNotFoundWhenAbsent() throws Exception {
        final Admission admission = AdmissionTestSupplierKit.getTestNonExistentAdmission();
        final Long id = admission.getId();
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveReturnsAdmissionWhenExists() throws Exception {
        final Admission admission = AdmissionTestSupplierKit.getTestAdmission();
        final Admission admissionSaved = admissionService.create(admission);
        final Long id = admissionSaved.getId();
        final AdmissionDto admissionDto = admissionDtoMapper.admissionToAdmissionDto(admissionSaved);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String admissionJson = objectMapper.writeValueAsString(admissionDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(admissionJson));
    }

    @Test
    public void testThatRetrieveAllReturnsEmptyWhenAbsent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testThatRetrieveAllReturnsListWhenExist() throws Exception {
        final Admission admission = AdmissionTestSupplierKit.getTestAdmission();
        final Admission admissionSaved = admissionService.create(admission);
        final AdmissionDto admissionDto = admissionDtoMapper.admissionToAdmissionDto(admissionSaved);
        final List<AdmissionDto> listAdmissionDto = new ArrayList<>(List.of(admissionDto));
        final ObjectMapper objectMapper = new ObjectMapper();
        final String admissionListJson = objectMapper.writeValueAsString(listAdmissionDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(admissionListJson));

    }

    @Test
    public void testThatDeleteAdmissionByIdReturnsHttp204WhenAbsent() throws Exception {
        final Admission admission = AdmissionTestSupplierKit.getTestAdmission();
        final Long id = admission.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteAdmissionByIdDeletesAdmission() throws Exception {
        final Admission admission = AdmissionTestSupplierKit.getTestAdmission();
        final Admission savedAdmission = admissionService.save(admission);
        final Long id = savedAdmission.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

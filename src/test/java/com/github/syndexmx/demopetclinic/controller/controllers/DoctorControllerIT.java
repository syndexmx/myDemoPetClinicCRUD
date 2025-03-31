package com.github.syndexmx.demopetclinic.controller.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.syndexmx.demopetclinic.controller.mappers.DoctorDtoMapper;
import com.github.syndexmx.demopetclinic.domain.Doctor;
import com.github.syndexmx.demopetclinic.domain.DoctorTestSupplierKit;
import com.github.syndexmx.demopetclinic.controller.dtos.DoctorDto;
import com.github.syndexmx.demopetclinic.services.DoctorService;
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
public class DoctorControllerIT {

    private final String ROOT_API_PATH = "/api/v0/doctors";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorDtoMapper doctorDtoMapper;

    // TODO : patch tests

    @Test
    public void testThatDoctorIsCreated() throws Exception {
        Doctor doctor = DoctorTestSupplierKit.getTestDoctor();
        final DoctorDto doctorDto = doctorDtoMapper.doctorToDoctorDto(doctor);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String doctorJson = objectMapper.writeValueAsString(doctorDto);
        mockMvc.perform(MockMvcRequestBuilders.post(ROOT_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(doctorJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        List<Doctor> savedDoctorList = doctorService.listAll();
        assertEquals(1, savedDoctorList.size());
        Doctor savedDoctor = savedDoctorList.get(0);
        final Long id = savedDoctor.getId();
        doctor.setId(id);
        assertEquals(doctor, savedDoctor);
    }

    @Test
    public void testThatDoctorIsUpdated() throws Exception {
        final Doctor doctor = DoctorTestSupplierKit.getTestDoctor();
        Doctor savedDoctor = doctorService.create(doctor);
        final Long id = savedDoctor.getId();
        Doctor modifiedDoctor = DoctorTestSupplierKit.getModifiedTestDoctor();
        modifiedDoctor.setId(id);
        final DoctorDto modifiedDoctorDto = doctorDtoMapper.doctorToDoctorDto(modifiedDoctor);
        final ObjectMapper modifiedObjectMapper = new ObjectMapper();
        final String modifiedDoctorJson = modifiedObjectMapper.writeValueAsString(modifiedDoctorDto);
        mockMvc.perform(MockMvcRequestBuilders.put(ROOT_API_PATH + "/" + id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifiedDoctorJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(modifiedDoctorJson));
    }

    @Test
    public void testThatRetrieveReturnsNotFoundWhenAbsent() throws Exception {
        final Doctor doctor = DoctorTestSupplierKit.getTestNonExistentDoctor();
        final Long id = doctor.getId();
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveReturnsDoctorWhenExists() throws Exception {
        final Doctor doctor = DoctorTestSupplierKit.getTestDoctor();
        final Doctor doctorSaved = doctorService.create(doctor);
        final Long id = doctorSaved.getId();
        final DoctorDto doctorDto = doctorDtoMapper.doctorToDoctorDto(doctorSaved);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String doctorJson = objectMapper.writeValueAsString(doctorDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(doctorJson));
    }

    @Test
    public void testThatRetrieveAllReturnsEmptyWhenAbsent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testThatRetrieveAllReturnsListWhenExist() throws Exception {
        final Doctor doctor = DoctorTestSupplierKit.getTestDoctor();
        final Doctor doctorSaved = doctorService.create(doctor);
        final DoctorDto doctorDto = doctorDtoMapper.doctorToDoctorDto(doctorSaved);
        final List<DoctorDto> listDoctorDto = new ArrayList<>(List.of(doctorDto));
        final ObjectMapper objectMapper = new ObjectMapper();
        final String doctorListJson = objectMapper.writeValueAsString(listDoctorDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(doctorListJson));

    }

    @Test
    public void testThatDeleteDoctorByIdReturnsHttp204WhenAbsent() throws Exception {
        final Doctor doctor = DoctorTestSupplierKit.getTestDoctor();
        final Long id = doctor.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteDoctorByIdDeletesDoctor() throws Exception {
        final Doctor doctor = DoctorTestSupplierKit.getTestDoctor();
        final Doctor savedDoctor = doctorService.save(doctor);
        final Long id = savedDoctor.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

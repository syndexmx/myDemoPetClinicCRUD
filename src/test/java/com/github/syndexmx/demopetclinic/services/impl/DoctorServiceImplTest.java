package com.github.syndexmx.demopetclinic.services.impl;


import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Doctor;
import com.github.syndexmx.demopetclinic.domain.DoctorTestSupplierKit;
import com.github.syndexmx.demopetclinic.repository.entities.DoctorEntity;
import com.github.syndexmx.demopetclinic.repository.mappers.DoctorEntityMapper;
import com.github.syndexmx.demopetclinic.repository.reporitories.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@TemplatedAnnotation
@ExtendWith(MockitoExtension.class)
public class DoctorServiceImplTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorServiceImpl underTest;

    @Autowired
    private DoctorEntityMapper doctorEntityMapper;

    @Test
    public void testThatDoctorIsCreated() {
        Doctor doctor = DoctorTestSupplierKit.getTestDoctor();
        DoctorEntity doctorEntity = doctorEntityMapper.doctorToDoctorEntity(doctor);
        when(doctorRepository.save(any())).thenReturn(doctorEntity);
        final Doctor savedDoctor = underTest.create(doctor);
        doctor.setId(savedDoctor.getId());
        assertEquals(doctor, savedDoctor);
    }

    @Test
    public void testThatDoctorIsSaved() {
        final Doctor doctor = DoctorTestSupplierKit.getTestDoctor();
        final DoctorEntity doctorEntity = doctorEntityMapper.doctorToDoctorEntity(doctor);
        when(doctorRepository.save(eq(doctorEntity))).thenReturn(doctorEntity);
        final Doctor savedDoctor = underTest.save(doctor);
        assertEquals(doctor, savedDoctor);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final Doctor nonExistentDoctor = DoctorTestSupplierKit.getTestNonExistentDoctor();
        final Long nonExistentId = nonExistentDoctor.getId().toString();
        when(doctorRepository.findById(eq(Long.parseLong(nonExistentId)))).thenReturn(Optional.empty());
        final Optional<Doctor> foundDoctor = underTest.findById(nonExistentId);
        assertEquals(Optional.empty(), foundDoctor);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final Doctor doctor = DoctorTestSupplierKit.getTestDoctor();
        final DoctorEntity doctorEntity = doctorEntityMapper.doctorToDoctorEntity(doctor);
        final Long idString = doctor.getId().toString();
        when(doctorRepository.findById(eq(Long.parseLong(idString)))).thenReturn(Optional.of(doctorEntity));
        final Optional<Doctor> foundDoctor = underTest.findById(idString);
        assertEquals(Optional.of(doctor), foundDoctor);
    }

    @Test
    public void testListDoctorsReturnsEmptyListWhenAbsent() {
        when(doctorRepository.findAll()).thenReturn(new ArrayList<DoctorEntity>());
        final List<Doctor> result = underTest.listAll();
        assertEquals(0, result.size());
    }

    @Test
    public void testListDoctorsReturnsListWhenExist() {
        final Doctor doctor = DoctorTestSupplierKit.getTestDoctor();
        final DoctorEntity doctorEntity = doctorEntityMapper.doctorToDoctorEntity(doctor);
        List<DoctorEntity> listOfExisting = new ArrayList<>(List.of(doctorEntity));
        when(doctorRepository.findAll()).thenReturn(listOfExisting);
        final List<Doctor> result = underTest.listAll();
        assertEquals(listOfExisting.size(), result.size());
    }

    @Test
    public void testThatIsPresentReturnsFalseWhenAbsent() {
        when(doctorRepository.existsById(any())).thenReturn(false);
        final Doctor nonExistentDoctor = DoctorTestSupplierKit.getTestNonExistentDoctor();
        final Long nonExistentUuid = nonExistentDoctor.getId().toString();
        boolean result = underTest.isPresent(nonExistentUuid);
        assertFalse(result);
    }

    @Test
    public void testThatIsPresentReturnsTrueWhenExists() {
        final Doctor doctor = DoctorTestSupplierKit.getTestDoctor();
        final Long idString = doctor.getId().toString();
        when(doctorRepository.existsById(Long.parseLong(idString))).thenReturn(true);
        boolean result = underTest.isPresent(idString);
        assertTrue(result);
    }

    @Test
    public void testThatDoctorIsPresentReturnsFalseWhenAbsent() {
        when(doctorRepository.existsById(any())).thenReturn(false);
        final Doctor nonExistentDoctor = DoctorTestSupplierKit.getTestNonExistentDoctor();
        boolean result = underTest.isPresent(nonExistentDoctor);
        assertFalse(result);
    }

    @Test
    public void testThatDoctorIsPresentReturnsTrueWhenExists() {
        final Doctor doctor = DoctorTestSupplierKit.getTestDoctor();
        final String idString = doctor.getId().toString();
        when(doctorRepository.existsById(Long.parseLong(idString))).thenReturn(true);
        boolean result = underTest.isPresent(doctor);
        assertTrue(result);
    }

    @Test
    public void testThatDeleteDoctorDeletesDoctor() {
        final Doctor doctor = DoctorTestSupplierKit.getTestDoctor();
        final Long idString = doctor.getId().toString();
        underTest.deleteById(idString);
        verify(doctorRepository).deleteById(eq(Long.parseLong(idString)));
    }
}

package com.github.syndexmx.demopetclinic.services.impl;


import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Admission;
import com.github.syndexmx.demopetclinic.domain.AdmissionTestSupplierKit;
import com.github.syndexmx.demopetclinic.repository.entities.AdmissionEntity;
import com.github.syndexmx.demopetclinic.repository.mappers.AdmissionEntityMapper;
import com.github.syndexmx.demopetclinic.repository.reporitories.AdmissionRepository;
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
public class AdmissionServiceImplTest {

    @Mock
    private AdmissionRepository admissionRepository;

    @InjectMocks
    private AdmissionServiceImpl underTest;

    @Autowired
    private AdmissionEntityMapper admissionEntityMapper;

    // TODO : patch tests

    @Test
    public void testThatAdmissionIsCreated() {
        Admission admission = AdmissionTestSupplierKit.getTestAdmission();
        AdmissionEntity admissionEntity = admissionEntityMapper.admissionToAdmissionEntity(admission);
        when(admissionRepository.save(any())).thenReturn(admissionEntity);
        final Admission savedAdmission = underTest.create(admission);
        admission.setId(savedAdmission.getId());
        assertEquals(admission, savedAdmission);
    }

    @Test
    public void testThatAdmissionIsSaved() {
        final Admission admission = AdmissionTestSupplierKit.getTestAdmission();
        final AdmissionEntity admissionEntity = admissionEntityMapper.admissionToAdmissionEntity(admission);
        when(admissionRepository.save(eq(admissionEntity))).thenReturn(admissionEntity);
        final Admission savedAdmission = underTest.save(admission);
        assertEquals(admission, savedAdmission);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final Admission nonExistentAdmission = AdmissionTestSupplierKit.getTestNonExistentAdmission();
        final Long nonExistentId = nonExistentAdmission.getId();
        when(admissionRepository.findById(eq(nonExistentId))).thenReturn(Optional.empty());
        final Optional<Admission> foundAdmission = underTest.findById(nonExistentId);
        assertEquals(Optional.empty(), foundAdmission);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final Admission admission = AdmissionTestSupplierKit.getTestAdmission();
        final AdmissionEntity admissionEntity = admissionEntityMapper.admissionToAdmissionEntity(admission);
        final Long idString = admission.getId();
        when(admissionRepository.findById(eq(idString))).thenReturn(Optional.of(admissionEntity));
        final Optional<Admission> foundAdmission = underTest.findById(idString);
        assertEquals(Optional.of(admission), foundAdmission);
    }

    @Test
    public void testListAdmissionsReturnsEmptyListWhenAbsent() {
        when(admissionRepository.findAll()).thenReturn(new ArrayList<AdmissionEntity>());
        final List<Admission> result = underTest.listAll();
        assertEquals(0, result.size());
    }

    @Test
    public void testListAdmissionsReturnsListWhenExist() {
        final Admission admission = AdmissionTestSupplierKit.getTestAdmission();
        final AdmissionEntity admissionEntity = admissionEntityMapper.admissionToAdmissionEntity(admission);
        List<AdmissionEntity> listOfExisting = new ArrayList<>(List.of(admissionEntity));
        when(admissionRepository.findAll()).thenReturn(listOfExisting);
        final List<Admission> result = underTest.listAll();
        assertEquals(listOfExisting.size(), result.size());
    }

    @Test
    public void testThatIsPresentReturnsFalseWhenAbsent() {
        when(admissionRepository.existsById(any())).thenReturn(false);
        final Admission nonExistentAdmission = AdmissionTestSupplierKit.getTestNonExistentAdmission();
        final Long nonExistentUuid = nonExistentAdmission.getId();
        boolean result = underTest.isPresent(nonExistentUuid);
        assertFalse(result);
    }

    @Test
    public void testThatIsPresentReturnsTrueWhenExists() {
        final Admission admission = AdmissionTestSupplierKit.getTestAdmission();
        final Long idString = admission.getId();
        when(admissionRepository.existsById(idString)).thenReturn(true);
        boolean result = underTest.isPresent(idString);
        assertTrue(result);
    }

    @Test
    public void testThatAdmissionIsPresentReturnsFalseWhenAbsent() {
        when(admissionRepository.existsById(any())).thenReturn(false);
        final Admission nonExistentAdmission = AdmissionTestSupplierKit.getTestNonExistentAdmission();
        boolean result = underTest.isPresent(nonExistentAdmission);
        assertFalse(result);
    }

    @Test
    public void testThatAdmissionIsPresentReturnsTrueWhenExists() {
        final Admission admission = AdmissionTestSupplierKit.getTestAdmission();
        final String idString = admission.getId().toString();
        when(admissionRepository.existsById(Long.parseLong(idString))).thenReturn(true);
        boolean result = underTest.isPresent(admission);
        assertTrue(result);
    }

    @Test
    public void testThatDeleteAdmissionDeletesAdmission() {
        final Admission admission = AdmissionTestSupplierKit.getTestAdmission();
        final Long idString = admission.getId();
        underTest.deleteById(idString);
        verify(admissionRepository).deleteById(eq(idString));
    }
}

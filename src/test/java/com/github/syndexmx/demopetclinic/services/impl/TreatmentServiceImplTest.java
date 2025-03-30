package com.github.syndexmx.demopetclinic.services.impl;


import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Treatment;
import com.github.syndexmx.demopetclinic.domain.TreatmentTestSupplierKit;
import com.github.syndexmx.demopetclinic.repository.entities.TreatmentEntity;
import com.github.syndexmx.demopetclinic.repository.mappers.TreatmentEntityMapper;
import com.github.syndexmx.demopetclinic.repository.reporitories.TreatmentRepository;
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
public class TreatmentServiceImplTest {

    @Mock
    private TreatmentRepository treatmentRepository;

    @InjectMocks
    private TreatmentServiceImpl underTest;

    @Autowired
    private TreatmentEntityMapper treatmentEntityMapper;

    @Test
    public void testThatTreatmentIsCreated() {
        Treatment treatment = TreatmentTestSupplierKit.getTestTreatment();
        TreatmentEntity treatmentEntity = treatmentEntityMapper.treatmentToTreatmentEntity(treatment);
        when(treatmentRepository.save(any())).thenReturn(treatmentEntity);
        final Treatment savedTreatment = underTest.create(treatment);
        treatment.setId(savedTreatment.getId());
        assertEquals(treatment, savedTreatment);
    }

    @Test
    public void testThatTreatmentIsSaved() {
        final Treatment treatment = TreatmentTestSupplierKit.getTestTreatment();
        final TreatmentEntity treatmentEntity = treatmentEntityMapper.treatmentToTreatmentEntity(treatment);
        when(treatmentRepository.save(eq(treatmentEntity))).thenReturn(treatmentEntity);
        final Treatment savedTreatment = underTest.save(treatment);
        assertEquals(treatment, savedTreatment);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final Treatment nonExistentTreatment = TreatmentTestSupplierKit.getTestNonExistentTreatment();
        final String nonExistentId = nonExistentTreatment.getId().toString();
        when(treatmentRepository.findById(eq(Long.parseLong(nonExistentId)))).thenReturn(Optional.empty());
        final Optional<Treatment> foundTreatment = underTest.findById(nonExistentId);
        assertEquals(Optional.empty(), foundTreatment);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final Treatment treatment = TreatmentTestSupplierKit.getTestTreatment();
        final TreatmentEntity treatmentEntity = treatmentEntityMapper.treatmentToTreatmentEntity(treatment);
        final String idString = treatment.getId().toString();
        when(treatmentRepository.findById(eq(Long.parseLong(idString)))).thenReturn(Optional.of(treatmentEntity));
        final Optional<Treatment> foundTreatment = underTest.findById(idString);
        assertEquals(Optional.of(treatment), foundTreatment);
    }

    @Test
    public void testListTreatmentsReturnsEmptyListWhenAbsent() {
        when(treatmentRepository.findAll()).thenReturn(new ArrayList<TreatmentEntity>());
        final List<Treatment> result = underTest.listAll();
        assertEquals(0, result.size());
    }

    @Test
    public void testListTreatmentsReturnsListWhenExist() {
        final Treatment treatment = TreatmentTestSupplierKit.getTestTreatment();
        final TreatmentEntity treatmentEntity = treatmentEntityMapper.treatmentToTreatmentEntity(treatment);
        List<TreatmentEntity> listOfExisting = new ArrayList<>(List.of(treatmentEntity));
        when(treatmentRepository.findAll()).thenReturn(listOfExisting);
        final List<Treatment> result = underTest.listAll();
        assertEquals(listOfExisting.size(), result.size());
    }

    @Test
    public void testThatIsPresentReturnsFalseWhenAbsent() {
        when(treatmentRepository.existsById(any())).thenReturn(false);
        final Treatment nonExistentTreatment = TreatmentTestSupplierKit.getTestNonExistentTreatment();
        final String nonExistentUuid = nonExistentTreatment.getId().toString();
        boolean result = underTest.isPresent(nonExistentUuid);
        assertFalse(result);
    }

    @Test
    public void testThatIsPresentReturnsTrueWhenExists() {
        final Treatment treatment = TreatmentTestSupplierKit.getTestTreatment();
        final String idString = treatment.getId().toString();
        when(treatmentRepository.existsById(Long.parseLong(idString))).thenReturn(true);
        boolean result = underTest.isPresent(idString);
        assertTrue(result);
    }

    @Test
    public void testThatTreatmentIsPresentReturnsFalseWhenAbsent() {
        when(treatmentRepository.existsById(any())).thenReturn(false);
        final Treatment nonExistentTreatment = TreatmentTestSupplierKit.getTestNonExistentTreatment();
        boolean result = underTest.isPresent(nonExistentTreatment);
        assertFalse(result);
    }

    @Test
    public void testThatTreatmentIsPresentReturnsTrueWhenExists() {
        final Treatment treatment = TreatmentTestSupplierKit.getTestTreatment();
        final String idString = treatment.getId().toString();
        when(treatmentRepository.existsById(Long.parseLong(idString))).thenReturn(true);
        boolean result = underTest.isPresent(treatment);
        assertTrue(result);
    }

    @Test
    public void testThatDeleteTreatmentDeletesTreatment() {
        final Treatment treatment = TreatmentTestSupplierKit.getTestTreatment();
        final String idString = treatment.getId().toString();
        underTest.deleteById(idString);
        verify(treatmentRepository).deleteById(eq(Long.parseLong(idString)));
    }
}

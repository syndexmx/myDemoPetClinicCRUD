package com.github.syndexmx.demopetclinic.services.impl;


import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Pet;
import com.github.syndexmx.demopetclinic.domain.PetTestSupplierKit;
import com.github.syndexmx.demopetclinic.repositories.entities.PetEntity;
import com.github.syndexmx.demopetclinic.repositories.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static com.github.syndexmx.demopetclinic.repositories.mappers.PetEntityMapper.petToPetEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

@TemplatedAnnotation
@ExtendWith(MockitoExtension.class)
public class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceImpl underTest;

    @Test
    public void testThatPetIsCreated() {
        Pet pet = PetTestSupplierKit.getTestPet();
        PetEntity petEntity = petToPetEntity(pet);
        when(petRepository.save(any())).thenReturn(petEntity);
        final Pet savedPet = underTest.create(pet);
        pet.setId(savedPet.getId());
        assertEquals(pet, savedPet);
    }

    @Test
    public void testThatPetIsSaved() {
        final Pet pet = PetTestSupplierKit.getTestPet();
        final PetEntity petEntity = petToPetEntity(pet);
        when(petRepository.save(eq(petEntity))).thenReturn(petEntity);
        final Pet savedPet = underTest.save(pet);
        assertEquals(pet, savedPet);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final Pet nonExistentPet = PetTestSupplierKit.getTestNonExistentPet();
        final String nonExistentId = nonExistentPet.getId().toString();
        when(petRepository.findById(eq(Long.parseLong(nonExistentId)))).thenReturn(Optional.empty());
        final Optional<Pet> foundPet = underTest.findById(nonExistentId);
        assertEquals(Optional.empty(), foundPet);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final Pet pet = PetTestSupplierKit.getTestPet();
        final PetEntity petEntity = petToPetEntity(pet);
        final String idString = pet.getId().toString();
        when(petRepository.findById(eq(Long.parseLong(idString)))).thenReturn(Optional.of(petEntity));
        final Optional<Pet> foundPet = underTest.findById(idString);
        assertEquals(Optional.of(pet), foundPet);
    }

    @Test
    public void testListPetsReturnsEmptyListWhenAbsent() {
        when(petRepository.findAll()).thenReturn(new ArrayList<PetEntity>());
        final List<Pet> result = underTest.listAll();
        assertEquals(0, result.size());
    }

    @Test
    public void testListPetsReturnsListWhenExist() {
        final Pet pet = PetTestSupplierKit.getTestPet();
        final PetEntity petEntity = petToPetEntity(pet);
        List<PetEntity> listOfExisting = new ArrayList<>(List.of(petEntity));
        when(petRepository.findAll()).thenReturn(listOfExisting);
        final List<Pet> result = underTest.listAll();
        assertEquals(listOfExisting.size(), result.size());
    }

    @Test
    public void testThatIsPresentReturnsFalseWhenAbsent() {
        when(petRepository.existsById(any())).thenReturn(false);
        final Pet nonExistentPet = PetTestSupplierKit.getTestNonExistentPet();
        final String nonExistentUuid = nonExistentPet.getId().toString();
        boolean result = underTest.isPresent(nonExistentUuid);
        assertFalse(result);
    }

    @Test
    public void testThatIsPresentReturnsTrueWhenExists() {
        final Pet pet = PetTestSupplierKit.getTestPet();
        final String idString = pet.getId().toString();
        when(petRepository.existsById(Long.parseLong(idString))).thenReturn(true);
        boolean result = underTest.isPresent(idString);
        assertTrue(result);
    }

    @Test
    public void testThatPetIsPresentReturnsFalseWhenAbsent() {
        when(petRepository.existsById(any())).thenReturn(false);
        final Pet nonExistentPet = PetTestSupplierKit.getTestNonExistentPet();
        boolean result = underTest.isPresent(nonExistentPet);
        assertFalse(result);
    }

    @Test
    public void testThatPetIsPresentReturnsTrueWhenExists() {
        final Pet pet = PetTestSupplierKit.getTestPet();
        final String idString = pet.getId().toString();
        when(petRepository.existsById(Long.parseLong(idString))).thenReturn(true);
        boolean result = underTest.isPresent(pet);
        assertTrue(result);
    }

    @Test
    public void testThatDeletePetDeletesPet() {
        final Pet pet = PetTestSupplierKit.getTestPet();
        final String idString = pet.getId().toString();
        underTest.deleteById(idString);
        verify(petRepository).deleteById(eq(Long.parseLong(idString)));
    }
}

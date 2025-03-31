package com.github.syndexmx.demopetclinic.services.impl;

import com.github.syndexmx.demopetclinic.domain.Pet;
import com.github.syndexmx.demopetclinic.domain.PetTestSupplierKit;
import com.github.syndexmx.demopetclinic.repository.entities.PetEntity;
import com.github.syndexmx.demopetclinic.repository.mappers.PetEntityMapper;
import com.github.syndexmx.demopetclinic.repository.reporitories.PetRepository;
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
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceImpl underTest;

    @Autowired
    private PetEntityMapper petEntityMapper;

    // TODO : patch tests

    @Test
    public void testThatPetIsCreated() {
        Pet pet = PetTestSupplierKit.getTestPet();
        PetEntity petEntity = petEntityMapper.petToPetEntity(pet);
        when(petRepository.save(any())).thenReturn(petEntity);
        final Pet savedPet = underTest.create(pet);
        pet.setId(savedPet.getId());
        assertEquals(pet, savedPet);
    }

    @Test
    public void testThatPetIsSaved() {
        final Pet pet = PetTestSupplierKit.getTestPet();
        final PetEntity petEntity = petEntityMapper.petToPetEntity(pet);
        when(petRepository.save(eq(petEntity))).thenReturn(petEntity);
        final Pet savedPet = underTest.save(pet);
        assertEquals(pet, savedPet);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final Pet nonExistentPet = PetTestSupplierKit.getTestNonExistentPet();
        final Long nonExistentId = nonExistentPet.getId();
        when(petRepository.findById(eq(nonExistentId))).thenReturn(Optional.empty());
        final Optional<Pet> foundPet = underTest.findById(nonExistentId);
        assertEquals(Optional.empty(), foundPet);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final Pet pet = PetTestSupplierKit.getTestPet();
        final PetEntity petEntity = petEntityMapper.petToPetEntity(pet);
        final Long idString = pet.getId();
        when(petRepository.findById(eq(idString))).thenReturn(Optional.of(petEntity));
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
        final PetEntity petEntity = petEntityMapper.petToPetEntity(pet);
        List<PetEntity> listOfExisting = new ArrayList<>(List.of(petEntity));
        when(petRepository.findAll()).thenReturn(listOfExisting);
        final List<Pet> result = underTest.listAll();
        assertEquals(listOfExisting.size(), result.size());
    }

    @Test
    public void testThatIsPresentReturnsFalseWhenAbsent() {
        when(petRepository.existsById(any())).thenReturn(false);
        final Pet nonExistentPet = PetTestSupplierKit.getTestNonExistentPet();
        final Long nonExistentUuid = nonExistentPet.getId();
        boolean result = underTest.isPresent(nonExistentUuid);
        assertFalse(result);
    }

    @Test
    public void testThatIsPresentReturnsTrueWhenExists() {
        final Pet pet = PetTestSupplierKit.getTestPet();
        final Long idString = pet.getId();
        when(petRepository.existsById(idString)).thenReturn(true);
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
        final Long idString = pet.getId();
        underTest.deleteById(idString);
        verify(petRepository).deleteById(eq(idString));
    }
}

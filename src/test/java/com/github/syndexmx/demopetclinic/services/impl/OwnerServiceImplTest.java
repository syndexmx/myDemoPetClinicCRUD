package com.github.syndexmx.demopetclinic.services.impl;


import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Owner;
import com.github.syndexmx.demopetclinic.domain.OwnerTestSupplierKit;
import com.github.syndexmx.demopetclinic.repository.entities.OwnerEntity;
import com.github.syndexmx.demopetclinic.repository.mappers.OwnerEntityMapper;
import com.github.syndexmx.demopetclinic.repository.reporitories.OwnerRepository;
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
public class OwnerServiceImplTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerServiceImpl underTest;

    @Autowired
    private OwnerEntityMapper ownerEntityMapper;

    // TODO : patch tests

    @Test
    public void testThatOwnerIsCreated() {
        Owner owner = OwnerTestSupplierKit.getTestOwner();
        OwnerEntity ownerEntity = ownerEntityMapper.ownerToOwnerEntity(owner);
        when(ownerRepository.save(any())).thenReturn(ownerEntity);
        final Owner savedOwner = underTest.create(owner);
        owner.setId(savedOwner.getId());
        assertEquals(owner, savedOwner);
    }

    @Test
    public void testThatOwnerIsSaved() {
        final Owner owner = OwnerTestSupplierKit.getTestOwner();
        final OwnerEntity ownerEntity = ownerEntityMapper.ownerToOwnerEntity(owner);
        when(ownerRepository.save(eq(ownerEntity))).thenReturn(ownerEntity);
        final Owner savedOwner = underTest.save(owner);
        assertEquals(owner, savedOwner);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final Owner nonExistentOwner = OwnerTestSupplierKit.getTestNonExistentOwner();
        final Long nonExistentId = nonExistentOwner.getId();
        when(ownerRepository.findById(eq(nonExistentId))).thenReturn(Optional.empty());
        final Optional<Owner> foundOwner = underTest.findById(nonExistentId);
        assertEquals(Optional.empty(), foundOwner);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final Owner owner = OwnerTestSupplierKit.getTestOwner();
        final OwnerEntity ownerEntity = ownerEntityMapper.ownerToOwnerEntity(owner);
        final Long idString = owner.getId();
        when(ownerRepository.findById(eq(idString))).thenReturn(Optional.of(ownerEntity));
        final Optional<Owner> foundOwner = underTest.findById(idString);
        assertEquals(Optional.of(owner), foundOwner);
    }

    @Test
    public void testListOwnersReturnsEmptyListWhenAbsent() {
        when(ownerRepository.findAll()).thenReturn(new ArrayList<OwnerEntity>());
        final List<Owner> result = underTest.listAll();
        assertEquals(0, result.size());
    }

    @Test
    public void testListOwnersReturnsListWhenExist() {
        final Owner owner = OwnerTestSupplierKit.getTestOwner();
        final OwnerEntity ownerEntity = ownerEntityMapper.ownerToOwnerEntity(owner);
        List<OwnerEntity> listOfExisting = new ArrayList<>(List.of(ownerEntity));
        when(ownerRepository.findAll()).thenReturn(listOfExisting);
        final List<Owner> result = underTest.listAll();
        assertEquals(listOfExisting.size(), result.size());
    }

    @Test
    public void testThatIsPresentReturnsFalseWhenAbsent() {
        when(ownerRepository.existsById(any())).thenReturn(false);
        final Owner nonExistentOwner = OwnerTestSupplierKit.getTestNonExistentOwner();
        final Long nonExistentUuid = nonExistentOwner.getId();
        boolean result = underTest.isPresent(nonExistentUuid);
        assertFalse(result);
    }

    @Test
    public void testThatIsPresentReturnsTrueWhenExists() {
        final Owner owner = OwnerTestSupplierKit.getTestOwner();
        final Long idString = owner.getId();
        when(ownerRepository.existsById(idString)).thenReturn(true);
        boolean result = underTest.isPresent(idString);
        assertTrue(result);
    }

    @Test
    public void testThatOwnerIsPresentReturnsFalseWhenAbsent() {
        when(ownerRepository.existsById(any())).thenReturn(false);
        final Owner nonExistentOwner = OwnerTestSupplierKit.getTestNonExistentOwner();
        boolean result = underTest.isPresent(nonExistentOwner);
        assertFalse(result);
    }

    @Test
    public void testThatOwnerIsPresentReturnsTrueWhenExists() {
        final Owner owner = OwnerTestSupplierKit.getTestOwner();
        final String idString = owner.getId().toString();
        when(ownerRepository.existsById(Long.parseLong(idString))).thenReturn(true);
        boolean result = underTest.isPresent(owner);
        assertTrue(result);
    }

    @Test
    public void testThatDeleteOwnerDeletesOwner() {
        final Owner owner = OwnerTestSupplierKit.getTestOwner();
        final Long idString = owner.getId();
        underTest.deleteById(idString);
        verify(ownerRepository).deleteById(eq(idString));
    }
}

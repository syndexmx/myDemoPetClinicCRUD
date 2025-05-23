package com.github.syndexmx.demopetclinic.services.impl;

import com.github.syndexmx.demopetclinic.domain.Address;
import com.github.syndexmx.demopetclinic.domain.AddressTestSupplierKit;
import com.github.syndexmx.demopetclinic.repository.entities.AddressEntity;
import com.github.syndexmx.demopetclinic.repository.mappers.AddressEntityMapper;
import com.github.syndexmx.demopetclinic.repository.reporitories.AddressRepository;
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
public class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl underTest;

    @Autowired
    private AddressEntityMapper addressEntityMapper;

    // TODO : patch tests

    @Test
    public void testThatAddressIsCreated() {
        Address address = AddressTestSupplierKit.getTestAddress();
        AddressEntity addressEntity = addressEntityMapper.addressToAddressEntity(address);
        when(addressRepository.save(any())).thenReturn(addressEntity);
        final Address savedAddress = underTest.create(address);
        address.setId(savedAddress.getId());
        assertEquals(address, savedAddress);
    }

    @Test
    public void testThatAddressIsSaved() {
        final Address address = AddressTestSupplierKit.getTestAddress();
        final AddressEntity addressEntity = addressEntityMapper.addressToAddressEntity(address);
        when(addressRepository.save(eq(addressEntity))).thenReturn(addressEntity);
        final Address savedAddress = underTest.save(address);
        assertEquals(address, savedAddress);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final Address nonExistentAddress = AddressTestSupplierKit.getTestNonExistentAddress();
        final Long nonExistentId = nonExistentAddress.getId();
        when(addressRepository.findById(eq(nonExistentId))).thenReturn(Optional.empty());
        final Optional<Address> foundAddress = underTest.findById(nonExistentId);
        assertEquals(Optional.empty(), foundAddress);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final Address address = AddressTestSupplierKit.getTestAddress();
        final AddressEntity addressEntity = addressEntityMapper.addressToAddressEntity(address);
        final Long idString = address.getId();
        when(addressRepository.findById(eq(idString))).thenReturn(Optional.of(addressEntity));
        final Optional<Address> foundAddress = underTest.findById(idString);
        assertEquals(Optional.of(address), foundAddress);
    }

    @Test
    public void testListAddresssReturnsEmptyListWhenAbsent() {
        when(addressRepository.findAll()).thenReturn(new ArrayList<AddressEntity>());
        final List<Address> result = underTest.listAll();
        assertEquals(0, result.size());
    }

    @Test
    public void testListAddresssReturnsListWhenExist() {
        final Address address = AddressTestSupplierKit.getTestAddress();
        final AddressEntity addressEntity = addressEntityMapper.addressToAddressEntity(address);
        List<AddressEntity> listOfExisting = new ArrayList<>(List.of(addressEntity));
        when(addressRepository.findAll()).thenReturn(listOfExisting);
        final List<Address> result = underTest.listAll();
        assertEquals(listOfExisting.size(), result.size());
    }

    @Test
    public void testThatIsPresentReturnsFalseWhenAbsent() {
        when(addressRepository.existsById(any())).thenReturn(false);
        final Address nonExistentAddress = AddressTestSupplierKit.getTestNonExistentAddress();
        final Long nonExistentUuid = nonExistentAddress.getId();
        boolean result = underTest.isPresent(nonExistentUuid);
        assertFalse(result);
    }

    @Test
    public void testThatIsPresentReturnsTrueWhenExists() {
        final Address address = AddressTestSupplierKit.getTestAddress();
        final Long idString = address.getId();
        when(addressRepository.existsById(idString)).thenReturn(true);
        boolean result = underTest.isPresent(idString);
        assertTrue(result);
    }

    @Test
    public void testThatAddressIsPresentReturnsFalseWhenAbsent() {
        when(addressRepository.existsById(any())).thenReturn(false);
        final Address nonExistentAddress = AddressTestSupplierKit.getTestNonExistentAddress();
        boolean result = underTest.isPresent(nonExistentAddress);
        assertFalse(result);
    }

    @Test
    public void testThatAddressIsPresentReturnsTrueWhenExists() {
        final Address address = AddressTestSupplierKit.getTestAddress();
        final String idString = address.getId().toString();
        when(addressRepository.existsById(Long.parseLong(idString))).thenReturn(true);
        boolean result = underTest.isPresent(address);
        assertTrue(result);
    }

    @Test
    public void testThatDeleteAddressDeletesAddress() {
        final Address address = AddressTestSupplierKit.getTestAddress();
        final Long idString = address.getId();
        underTest.deleteById(idString);
        verify(addressRepository).deleteById(eq(idString));
    }
}

package com.github.syndexmx.demopetclinic.services.impl;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Address;
import com.github.syndexmx.demopetclinic.repository.entities.AddressEntity;
import com.github.syndexmx.demopetclinic.repository.mappers.AddressEntityMapper;
import com.github.syndexmx.demopetclinic.repository.reporitories.AddressRepository;
import com.github.syndexmx.demopetclinic.services.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@TemplatedAnnotation
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressEntityMapper addressEntityMapper;

    @Autowired
    private AddressServiceImpl(AddressRepository addressRepository, AddressEntityMapper addressEntityMapper) {
        this.addressRepository = addressRepository;
        this.addressEntityMapper = addressEntityMapper;
    }

    @Override
    public Address create(Address address) {
        Random random = new Random();Long spoofId = random.nextLong();
        address.setId(spoofId);
        final AddressEntity savedEntity = addressRepository
                .save(addressEntityMapper.addressToAddressEntity(address));
        final Address savedAddress = addressEntityMapper.addressEntityToAddress(savedEntity);
        return savedAddress;
    }

    @Override
    public Address save(Address address) {
        final AddressEntity savedEntity = addressRepository
                .save(addressEntityMapper.addressToAddressEntity(address));
        final Address savedAddress = addressEntityMapper.addressEntityToAddress(savedEntity);
        return savedAddress;
    }

    @Override
    public Optional<Address> findById(String addressId) {
        final Optional<AddressEntity> addressEntityFound = addressRepository
                .findById(Long.parseLong(addressId));
        final Optional<Address> addressFound = addressEntityFound.map(addressEntity ->
                addressEntityMapper.addressEntityToAddress(addressEntity));
        return addressFound;
    }

    @Override
    public List<Address> listAll() {
        final List<AddressEntity> listOfFoundAddressEntities = addressRepository.findAll();
        final List<Address> listOfFoundAddresss =listOfFoundAddressEntities.stream()
                .map(entity ->
                        addressEntityMapper.addressEntityToAddress(entity)).toList();
        return listOfFoundAddresss;
    }

    @Override
    public boolean isPresent(String addressId) {
        return addressRepository.existsById(Long.parseLong(addressId));
    }

    @Override
    public boolean isPresent(Address address) {
        return addressRepository.existsById(address.getId());
    }

    @Override
    public void deleteById(String addressId) {
        try {
            addressRepository.deleteById(Long.parseLong(addressId));
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent address");
        }
    }

}

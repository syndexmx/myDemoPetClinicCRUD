package com.github.syndexmx.demopetclinic.services.impl;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Owner;
import com.github.syndexmx.demopetclinic.repository.entities.OwnerEntity;
import com.github.syndexmx.demopetclinic.repository.mappers.OwnerEntityMapper;
import com.github.syndexmx.demopetclinic.repository.reporitories.OwnerRepository;
import com.github.syndexmx.demopetclinic.services.OwnerService;
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
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerEntityMapper ownerEntityMapper;

    @Autowired
    private OwnerServiceImpl(OwnerRepository ownerRepository, OwnerEntityMapper ownerEntityMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerEntityMapper = ownerEntityMapper;
    }

    @Override
    public Owner create(Owner owner) {
        Random random = new Random();Long spoofId = random.nextLong();
        owner.setId(spoofId);
        final OwnerEntity savedEntity = ownerRepository
                .save(ownerEntityMapper.ownerToOwnerEntity(owner));
        final Owner savedOwner = ownerEntityMapper.ownerEntityToOwner(savedEntity);
        return savedOwner;
    }

    @Override
    public Owner save(Owner owner) {
        final OwnerEntity savedEntity = ownerRepository
                .save(ownerEntityMapper.ownerToOwnerEntity(owner));
        final Owner savedOwner = ownerEntityMapper.ownerEntityToOwner(savedEntity);
        return savedOwner;
    }

    @Override
    public Optional<Owner> findById(Long ownerId) {
        final Optional<OwnerEntity> ownerEntityFound = ownerRepository
                .findById(ownerId);
        final Optional<Owner> ownerFound = ownerEntityFound.map(ownerEntity ->
                ownerEntityMapper.ownerEntityToOwner(ownerEntity));
        return ownerFound;
    }

    @Override
    public List<Owner> listAll() {
        final List<OwnerEntity> listOfFoundOwnerEntities = ownerRepository.findAll();
        final List<Owner> listOfFoundOwners =listOfFoundOwnerEntities.stream()
                .map(entity ->
                        ownerEntityMapper.ownerEntityToOwner(entity)).toList();
        return listOfFoundOwners;
    }

    @Override
    public boolean isPresent(Long ownerId) {
        return ownerRepository.existsById(ownerId);
    }

    @Override
    public boolean isPresent(Owner owner) {
        return ownerRepository.existsById(owner.getId());
    }

    @Override
    public void deleteById(Long ownerId) {
        try {
            ownerRepository.deleteById(ownerId);
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent owner");
        }
    }

}

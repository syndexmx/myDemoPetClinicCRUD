package com.github.syndexmx.demopetclinic.services.impl;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Owner;
import com.github.syndexmx.demopetclinic.repository.entities.OwnerEntity;
import com.github.syndexmx.demopetclinic.repository.reporitories.OwnerRepository;
import com.github.syndexmx.demopetclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;


import static com.github.syndexmx.demopetclinic.repository.mappers.OwnerEntityMapper.ownerEntityToOwner;
import static com.github.syndexmx.demopetclinic.repository.mappers.OwnerEntityMapper.ownerToOwnerEntity;

@TemplatedAnnotation
@Service
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    private OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner create(Owner owner) {
        Random random = new Random();Long spoofId = random.nextLong();
        owner.setId(spoofId);
        final OwnerEntity savedEntity = ownerRepository.save(ownerToOwnerEntity(owner));
        final Owner savedOwner = ownerEntityToOwner(savedEntity);
        return savedOwner;
    }

    @Override
    public Owner save(Owner owner) {
        final OwnerEntity savedEntity = ownerRepository.save(ownerToOwnerEntity(owner));
        final Owner savedOwner = ownerEntityToOwner(savedEntity);
        return savedOwner;
    }

    @Override
    public Optional<Owner> findById(String ownerId) {
        final Optional<OwnerEntity> ownerEntityFound = ownerRepository
                .findById(Long.parseLong(ownerId));
        final Optional<Owner> ownerFound = ownerEntityFound.map(ownerEntity ->
                ownerEntityToOwner(ownerEntity));
        return ownerFound;
    }

    @Override
    public List<Owner> listAll() {
        final List<OwnerEntity> listOfFoundOwnerEntities = ownerRepository.findAll();
        final List<Owner> listOfFoundOwners =listOfFoundOwnerEntities.stream()
                .map(entity -> ownerEntityToOwner(entity)).toList();
        return listOfFoundOwners;
    }

    @Override
    public boolean isPresent(String ownerId) {
        return ownerRepository.existsById(Long.parseLong(ownerId));
    }

    @Override
    public boolean isPresent(Owner owner) {
        return ownerRepository.existsById(owner.getId());
    }

    @Override
    public void deleteById(String ownerId) {
        try {
            ownerRepository.deleteById(Long.parseLong(ownerId));
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent owner");
        }
    }

}

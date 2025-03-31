package com.github.syndexmx.demopetclinic.services.impl;

import com.github.syndexmx.demopetclinic.domain.Owner;
import com.github.syndexmx.demopetclinic.domain.Pet;
import com.github.syndexmx.demopetclinic.repository.entities.PetEntity;
import com.github.syndexmx.demopetclinic.repository.mappers.PetEntityMapper;
import com.github.syndexmx.demopetclinic.repository.reporitories.PetRepository;
import com.github.syndexmx.demopetclinic.services.OwnerService;
import com.github.syndexmx.demopetclinic.services.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final PetEntityMapper petEntityMapper;
    private final OwnerService ownerService;

    @Autowired
    private PetServiceImpl(PetRepository petRepository, PetEntityMapper petEntityMapper, OwnerService ownerService) {
        this.petRepository = petRepository;
        this.petEntityMapper = petEntityMapper;
        this.ownerService = ownerService;
    }

    @Override
    public Pet create(Pet pet) {
        Random random = new Random();
        Long spoofId = random.nextLong();
        pet.setId(spoofId);
        final PetEntity savedEntity = petRepository.save(petEntityMapper
                .petToPetEntity(pet));
        final Pet savedPet = petEntityMapper.petEntityToPet(savedEntity);
        casscadeAssignOwner(savedPet.getId(), savedPet.getOwnerId());
        return savedPet;
    }

    @Override
    public Pet save(Pet pet) {
        final PetEntity savedEntity = petRepository.save(petEntityMapper.petToPetEntity(pet));
        final Pet savedPet = petEntityMapper.petEntityToPet(savedEntity);
        return savedPet;
    }

    @Override
    public Optional<Pet> findById(Long petId) {
        final Optional<PetEntity> petEntityFound = petRepository
                .findById(petId);
        final Optional<Pet> petFound = petEntityFound.map(petEntity ->
                petEntityMapper.petEntityToPet(petEntity));
        return petFound;
    }

    @Override
    public List<Pet> listAll() {
        final List<PetEntity> listOfFoundPetEntities = petRepository.findAll();
        final List<Pet> listOfFoundPets =listOfFoundPetEntities.stream()
                .map(entity -> petEntityMapper.petEntityToPet(entity)).toList();
        return listOfFoundPets;
    }

    @Override
    public boolean isPresent(Long petId) {
        return petRepository.existsById(petId);
    }

    @Override
    public boolean isPresent(Pet pet) {
        return petRepository.existsById(pet.getId());
    }

    @Override
    public void deleteById(Long petId) {
        try {
            //PetEntity petEntity = petRepository.findById(petId).orElseThrow();
            //Owner owner = ownerService.findById(petEntity.getOwner().getOwnerId()).orElseThrow();
            //casscadeDeleteFromOwner(petId, owner.getId());
            petRepository.deleteById(petId);
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent pet");
        }
    }

    @Override
    public void casscadeAssignOwner(Long petId, Long ownerId) {
        Owner owner = ownerService.findById(ownerId).orElseThrow();
        List<Long> updatedPetList = new ArrayList<>();
        updatedPetList.addAll(owner.getPetIdList());
        updatedPetList.add(petId);
        owner.setPetIdList(updatedPetList);
        ownerService.save(owner);
    }

    @Override
    public void casscadeDeleteFromOwner(Long petId, Long ownerId) {
        Owner owner = ownerService.findById(ownerId).orElseThrow();
        List<Long> updatedPetList = new ArrayList<>();
        updatedPetList.addAll(owner.getPetIdList());
        updatedPetList.remove(petId);
        owner.setPetIdList(updatedPetList);
        ownerService.save(owner);
    }

}

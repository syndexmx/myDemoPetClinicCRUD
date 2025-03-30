package com.github.syndexmx.demopetclinic.services.impl;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Pet;
import com.github.syndexmx.demopetclinic.repository.entities.PetEntity;
import com.github.syndexmx.demopetclinic.repository.mappers.PetEntityMapper;
import com.github.syndexmx.demopetclinic.repository.reporitories.PetRepository;
import com.github.syndexmx.demopetclinic.services.PetService;
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
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final PetEntityMapper petEntityMapper;

    @Autowired
    private PetServiceImpl(PetRepository petRepository, PetEntityMapper petEntityMapper) {
        this.petRepository = petRepository;
        this.petEntityMapper = petEntityMapper;
    }

    @Override
    public Pet create(Pet pet) {
        Random random = new Random();Long spoofId = random.nextLong();
        pet.setId(spoofId);
        final PetEntity savedEntity = petRepository.save(petEntityMapper.petToPetEntity(pet));
        final Pet savedPet = petEntityMapper.petEntityToPet(savedEntity);
        return savedPet;
    }

    @Override
    public Pet save(Pet pet) {
        final PetEntity savedEntity = petRepository.save(petEntityMapper.petToPetEntity(pet));
        final Pet savedPet = petEntityMapper.petEntityToPet(savedEntity);
        return savedPet;
    }

    @Override
    public Optional<Pet> findById(String petId) {
        final Optional<PetEntity> petEntityFound = petRepository
                .findById(Long.parseLong(petId));
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
    public boolean isPresent(String petId) {
        return petRepository.existsById(Long.parseLong(petId));
    }

    @Override
    public boolean isPresent(Pet pet) {
        return petRepository.existsById(pet.getId());
    }

    @Override
    public void deleteById(String petId) {
        try {
            petRepository.deleteById(Long.parseLong(petId));
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent pet");
        }
    }

}

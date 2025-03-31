package com.github.syndexmx.demopetclinic.services.impl;

import com.github.syndexmx.demopetclinic.domain.Address;
import com.github.syndexmx.demopetclinic.domain.Admission;
import com.github.syndexmx.demopetclinic.domain.Pet;
import com.github.syndexmx.demopetclinic.repository.entities.AdmissionEntity;
import com.github.syndexmx.demopetclinic.repository.mappers.AdmissionEntityMapper;
import com.github.syndexmx.demopetclinic.repository.reporitories.AdmissionRepository;
import com.github.syndexmx.demopetclinic.services.AdmissionService;
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
public class AdmissionServiceImpl implements AdmissionService {

    private final AdmissionRepository admissionRepository;
    private final AdmissionEntityMapper admissionEntityMapper;
    private final PetService petService;

    @Autowired
    private AdmissionServiceImpl(AdmissionRepository admissionRepository,
                                 AdmissionEntityMapper admissionEntityMapper,
                                 PetService petService) {
        this.admissionRepository = admissionRepository;
        this.admissionEntityMapper = admissionEntityMapper;
        this.petService = petService;
    }

    @Override
    public Admission create(Admission admission) {
        Random random = new Random();
        Long spoofId = random.nextLong();
        admission.setId(spoofId);
        final AdmissionEntity savedEntity = admissionRepository
                .save(admissionEntityMapper.admissionToAdmissionEntity(admission));
        final Admission savedAdmission = admissionEntityMapper.admissionEntityToAdmission(savedEntity);
        casscadeAssignPet(savedAdmission.getId(), savedAdmission.getPetId());
        return savedAdmission;
    }

    @Override
    public Admission save(Admission admission) {
        final AdmissionEntity savedEntity = admissionRepository
                .save(admissionEntityMapper.admissionToAdmissionEntity(admission));
        final Admission savedAdmission = admissionEntityMapper.admissionEntityToAdmission(savedEntity);
        return savedAdmission;
    }

    @Override
    public Optional<Admission> findById(Long admissionId) {
        final Optional<AdmissionEntity> admissionEntityFound = admissionRepository
                .findById(admissionId);
        final Optional<Admission> admissionFound = admissionEntityFound.map(admissionEntity ->
                admissionEntityMapper.admissionEntityToAdmission(admissionEntity));
        return admissionFound;
    }

    @Override
    public List<Admission> listAll() {
        final List<AdmissionEntity> listOfFoundAdmissionEntities = admissionRepository.findAll();
        final List<Admission> listOfFoundAdmissions =listOfFoundAdmissionEntities.stream()
                .map(entity -> admissionEntityMapper.admissionEntityToAdmission(entity)).toList();
        return listOfFoundAdmissions;
    }

    @Override
    public boolean isPresent(Long admissionId) {
        return admissionRepository.existsById(admissionId);
    }

    @Override
    public boolean isPresent(Admission admission) {
        return admissionRepository.existsById(admission.getId());
    }

    @Override
    public void deleteById(Long admissionId) {
        try {
            admissionRepository.deleteById(admissionId);
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent admission");
        }
    }

    @Override
    public void casscadeAssignPet(Long admissionId, Long petId) {
        Pet pet = petService.findById(petId).orElseThrow();
        List<Long> updatedOwnerList = new ArrayList<>();
        updatedOwnerList.addAll(pet.getAdmissionIdList());
        updatedOwnerList.add(admissionId);
        pet.setAdmissionIdList(updatedOwnerList);
        petService.save(pet);
    }

}

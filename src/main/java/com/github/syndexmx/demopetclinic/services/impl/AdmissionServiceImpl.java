package com.github.syndexmx.demopetclinic.services.impl;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Admission;
import com.github.syndexmx.demopetclinic.repositories.entities.AdmissionEntity;
import com.github.syndexmx.demopetclinic.repositories.AdmissionRepository;
import com.github.syndexmx.demopetclinic.services.AdmissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;


import static com.github.syndexmx.demopetclinic.repositories.mappers.AdmissionEntityMapper.admissionEntityToAdmission;
import static com.github.syndexmx.demopetclinic.repositories.mappers.AdmissionEntityMapper.admissionToAdmissionEntity;

@TemplatedAnnotation
@Service
@Slf4j
public class AdmissionServiceImpl implements AdmissionService {

    private final AdmissionRepository admissionRepository;

    @Autowired
    private AdmissionServiceImpl(AdmissionRepository admissionRepository) {
        this.admissionRepository = admissionRepository;
    }

    @Override
    public Admission create(Admission admission) {
        Random random = new Random();Long spoofId = random.nextLong();
        admission.setId(spoofId);
        final AdmissionEntity savedEntity = admissionRepository.save(admissionToAdmissionEntity(admission));
        final Admission savedAdmission = admissionEntityToAdmission(savedEntity);
        return savedAdmission;
    }

    @Override
    public Admission save(Admission admission) {
        final AdmissionEntity savedEntity = admissionRepository.save(admissionToAdmissionEntity(admission));
        final Admission savedAdmission = admissionEntityToAdmission(savedEntity);
        return savedAdmission;
    }

    @Override
    public Optional<Admission> findById(String admissionId) {
        final Optional<AdmissionEntity> admissionEntityFound = admissionRepository
                .findById(Long.parseLong(admissionId));
        final Optional<Admission> admissionFound = admissionEntityFound.map(admissionEntity ->
                admissionEntityToAdmission(admissionEntity));
        return admissionFound;
    }

    @Override
    public List<Admission> listAll() {
        final List<AdmissionEntity> listOfFoundAdmissionEntities = admissionRepository.findAll();
        final List<Admission> listOfFoundAdmissions =listOfFoundAdmissionEntities.stream()
                .map(entity -> admissionEntityToAdmission(entity)).toList();
        return listOfFoundAdmissions;
    }

    @Override
    public boolean isPresent(String admissionId) {
        return admissionRepository.existsById(Long.parseLong(admissionId));
    }

    @Override
    public boolean isPresent(Admission admission) {
        return admissionRepository.existsById(admission.getId());
    }

    @Override
    public void deleteById(String admissionId) {
        try {
            admissionRepository.deleteById(Long.parseLong(admissionId));
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent admission");
        }
    }

}

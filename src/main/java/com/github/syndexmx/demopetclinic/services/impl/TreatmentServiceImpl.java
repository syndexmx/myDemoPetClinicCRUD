package com.github.syndexmx.demopetclinic.services.impl;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Treatment;
import com.github.syndexmx.demopetclinic.repositories.entities.TreatmentEntity;
import com.github.syndexmx.demopetclinic.repositories.TreatmentRepository;
import com.github.syndexmx.demopetclinic.services.TreatmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;


import static com.github.syndexmx.demopetclinic.repositories.mappers.TreatmentEntityMapper.treatmentEntityToTreatment;
import static com.github.syndexmx.demopetclinic.repositories.mappers.TreatmentEntityMapper.treatmentToTreatmentEntity;

@TemplatedAnnotation
@Service
@Slf4j
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;

    @Autowired
    private TreatmentServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public Treatment create(Treatment treatment) {
        Random random = new Random();Long spoofId = random.nextLong();
        treatment.setId(spoofId);
        final TreatmentEntity savedEntity = treatmentRepository.save(treatmentToTreatmentEntity(treatment));
        final Treatment savedTreatment = treatmentEntityToTreatment(savedEntity);
        return savedTreatment;
    }

    @Override
    public Treatment save(Treatment treatment) {
        final TreatmentEntity savedEntity = treatmentRepository.save(treatmentToTreatmentEntity(treatment));
        final Treatment savedTreatment = treatmentEntityToTreatment(savedEntity);
        return savedTreatment;
    }

    @Override
    public Optional<Treatment> findById(String treatmentId) {
        final Optional<TreatmentEntity> treatmentEntityFound = treatmentRepository
                .findById(Long.parseLong(treatmentId));
        final Optional<Treatment> treatmentFound = treatmentEntityFound.map(treatmentEntity ->
                treatmentEntityToTreatment(treatmentEntity));
        return treatmentFound;
    }

    @Override
    public List<Treatment> listAll() {
        final List<TreatmentEntity> listOfFoundTreatmentEntities = treatmentRepository.findAll();
        final List<Treatment> listOfFoundTreatments =listOfFoundTreatmentEntities.stream()
                .map(entity -> treatmentEntityToTreatment(entity)).toList();
        return listOfFoundTreatments;
    }

    @Override
    public boolean isPresent(String treatmentId) {
        return treatmentRepository.existsById(Long.parseLong(treatmentId));
    }

    @Override
    public boolean isPresent(Treatment treatment) {
        return treatmentRepository.existsById(treatment.getId());
    }

    @Override
    public void deleteById(String treatmentId) {
        try {
            treatmentRepository.deleteById(Long.parseLong(treatmentId));
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent treatment");
        }
    }

}

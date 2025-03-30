package com.github.syndexmx.demopetclinic.services.impl;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Treatment;
import com.github.syndexmx.demopetclinic.repository.entities.TreatmentEntity;
import com.github.syndexmx.demopetclinic.repository.mappers.TreatmentEntityMapper;
import com.github.syndexmx.demopetclinic.repository.reporitories.TreatmentRepository;
import com.github.syndexmx.demopetclinic.services.TreatmentService;
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
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final TreatmentEntityMapper treatmentEntityMapper;

    @Autowired
    private TreatmentServiceImpl(TreatmentRepository treatmentRepository, TreatmentEntityMapper treatmentEntityMapper) {
        this.treatmentRepository = treatmentRepository;
        this.treatmentEntityMapper = treatmentEntityMapper;
    }

    @Override
    public Treatment create(Treatment treatment) {
        Random random = new Random();Long spoofId = random.nextLong();
        treatment.setId(spoofId);
        final TreatmentEntity savedEntity = treatmentRepository
                .save(treatmentEntityMapper.treatmentToTreatmentEntity(treatment));
        final Treatment savedTreatment = treatmentEntityMapper.treatmentEntityToTreatment(savedEntity);
        return savedTreatment;
    }

    @Override
    public Treatment save(Treatment treatment) {
        final TreatmentEntity savedEntity = treatmentRepository
                .save(treatmentEntityMapper.treatmentToTreatmentEntity(treatment));
        final Treatment savedTreatment = treatmentEntityMapper.treatmentEntityToTreatment(savedEntity);
        return savedTreatment;
    }

    @Override
    public Optional<Treatment> findById(String treatmentId) {
        final Optional<TreatmentEntity> treatmentEntityFound = treatmentRepository
                .findById(Long.parseLong(treatmentId));
        final Optional<Treatment> treatmentFound = treatmentEntityFound.map(treatmentEntity ->
                treatmentEntityMapper.treatmentEntityToTreatment(treatmentEntity));
        return treatmentFound;
    }

    @Override
    public List<Treatment> listAll() {
        final List<TreatmentEntity> listOfFoundTreatmentEntities = treatmentRepository.findAll();
        final List<Treatment> listOfFoundTreatments =listOfFoundTreatmentEntities.stream()
                .map(entity ->
                        treatmentEntityMapper.treatmentEntityToTreatment(entity)).toList();
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

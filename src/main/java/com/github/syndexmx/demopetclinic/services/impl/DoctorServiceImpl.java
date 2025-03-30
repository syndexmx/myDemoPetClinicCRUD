package com.github.syndexmx.demopetclinic.services.impl;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Doctor;
import com.github.syndexmx.demopetclinic.repository.entities.DoctorEntity;
import com.github.syndexmx.demopetclinic.repository.mappers.DoctorEntityMapper;
import com.github.syndexmx.demopetclinic.repository.reporitories.DoctorRepository;
import com.github.syndexmx.demopetclinic.services.DoctorService;
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
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorEntityMapper doctorEntityMapper;

    @Autowired
    private DoctorServiceImpl(DoctorRepository doctorRepository, DoctorEntityMapper doctorEntityMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorEntityMapper = doctorEntityMapper;
    }

    @Override
    public Doctor create(Doctor doctor) {
        Random random = new Random();Long spoofId = random.nextLong();
        doctor.setId(spoofId);
        final DoctorEntity savedEntity = doctorRepository
                .save(doctorEntityMapper.doctorToDoctorEntity(doctor));
        final Doctor savedDoctor = doctorEntityMapper.doctorEntityToDoctor(savedEntity);
        return savedDoctor;
    }

    @Override
    public Doctor save(Doctor doctor) {
        final DoctorEntity savedEntity = doctorRepository
                .save(doctorEntityMapper.doctorToDoctorEntity(doctor));
        final Doctor savedDoctor = doctorEntityMapper.doctorEntityToDoctor(savedEntity);
        return savedDoctor;
    }

    @Override
    public Optional<Doctor> findById(String doctorId) {
        final Optional<DoctorEntity> doctorEntityFound = doctorRepository
                .findById(Long.parseLong(doctorId));
        final Optional<Doctor> doctorFound = doctorEntityFound.map(doctorEntity ->
                doctorEntityMapper.doctorEntityToDoctor(doctorEntity));
        return doctorFound;
    }

    @Override
    public List<Doctor> listAll() {
        final List<DoctorEntity> listOfFoundDoctorEntities = doctorRepository.findAll();
        final List<Doctor> listOfFoundDoctors =listOfFoundDoctorEntities.stream()
                .map(entity -> doctorEntityMapper.doctorEntityToDoctor(entity)).toList();
        return listOfFoundDoctors;
    }

    @Override
    public boolean isPresent(String doctorId) {
        return doctorRepository.existsById(Long.parseLong(doctorId));
    }

    @Override
    public boolean isPresent(Doctor doctor) {
        return doctorRepository.existsById(doctor.getId());
    }

    @Override
    public void deleteById(String doctorId) {
        try {
            doctorRepository.deleteById(Long.parseLong(doctorId));
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent doctor");
        }
    }

}

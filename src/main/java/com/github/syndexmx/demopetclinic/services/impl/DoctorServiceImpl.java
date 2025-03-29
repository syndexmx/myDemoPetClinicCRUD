package com.github.syndexmx.demopetclinic.services.impl;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Doctor;
import com.github.syndexmx.demopetclinic.repository.entities.DoctorEntity;
import com.github.syndexmx.demopetclinic.repository.reporitories.DoctorRepository;
import com.github.syndexmx.demopetclinic.services.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;


import static com.github.syndexmx.demopetclinic.repository.mappers.DoctorEntityMapper.doctorEntityToDoctor;
import static com.github.syndexmx.demopetclinic.repository.mappers.DoctorEntityMapper.doctorToDoctorEntity;

@TemplatedAnnotation
@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    private DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor create(Doctor doctor) {
        Random random = new Random();Long spoofId = random.nextLong();
        doctor.setId(spoofId);
        final DoctorEntity savedEntity = doctorRepository.save(doctorToDoctorEntity(doctor));
        final Doctor savedDoctor = doctorEntityToDoctor(savedEntity);
        return savedDoctor;
    }

    @Override
    public Doctor save(Doctor doctor) {
        final DoctorEntity savedEntity = doctorRepository.save(doctorToDoctorEntity(doctor));
        final Doctor savedDoctor = doctorEntityToDoctor(savedEntity);
        return savedDoctor;
    }

    @Override
    public Optional<Doctor> findById(String doctorId) {
        final Optional<DoctorEntity> doctorEntityFound = doctorRepository
                .findById(Long.parseLong(doctorId));
        final Optional<Doctor> doctorFound = doctorEntityFound.map(doctorEntity ->
                doctorEntityToDoctor(doctorEntity));
        return doctorFound;
    }

    @Override
    public List<Doctor> listAll() {
        final List<DoctorEntity> listOfFoundDoctorEntities = doctorRepository.findAll();
        final List<Doctor> listOfFoundDoctors =listOfFoundDoctorEntities.stream()
                .map(entity -> doctorEntityToDoctor(entity)).toList();
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

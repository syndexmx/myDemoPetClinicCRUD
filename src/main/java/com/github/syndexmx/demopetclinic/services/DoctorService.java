package com.github.syndexmx.demopetclinic.services;

import com.github.syndexmx.demopetclinic.domain.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DoctorService {

    Doctor create(Doctor doctor);

    Doctor save(Doctor doctor);

    Optional<Doctor> findById(Long doctorId);

    List<Doctor> listAll();

    boolean isPresent(Long doctorId);

    boolean isPresent(Doctor doctor);

    void deleteById(Long doctorId);

}

package com.github.syndexmx.demopetclinic.services;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@TemplatedAnnotation
@Service
public interface DoctorService {

    Doctor create(Doctor doctor);

    Doctor save(Doctor doctor);

    Optional<Doctor> findById(String doctorId);

    List<Doctor> listAll();

    boolean isPresent(String doctorId);

    boolean isPresent(Doctor doctor);

    void deleteById(String doctorId);

}

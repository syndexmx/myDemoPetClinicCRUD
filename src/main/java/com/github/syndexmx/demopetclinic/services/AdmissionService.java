package com.github.syndexmx.demopetclinic.services;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Admission;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@TemplatedAnnotation
@Service
public interface AdmissionService {

    Admission create(Admission admission);

    Admission save(Admission admission);

    Optional<Admission> findById(String admissionId);

    List<Admission> listAll();

    boolean isPresent(String admissionId);

    boolean isPresent(Admission admission);

    void deleteById(String admissionId);

}

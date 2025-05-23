package com.github.syndexmx.demopetclinic.services;

import com.github.syndexmx.demopetclinic.domain.Admission;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AdmissionService {

    Admission create(Admission admission);

    Admission save(Admission admission);

    Optional<Admission> findById(Long admissionId);

    List<Admission> listAll();

    boolean isPresent(Long admissionId);

    boolean isPresent(Admission admission);

    void deleteById(Long admissionId);

    public void casscadeAssignPet(Long admissionId, Long petId);

}

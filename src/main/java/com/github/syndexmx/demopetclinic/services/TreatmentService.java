package com.github.syndexmx.demopetclinic.services;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Treatment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@TemplatedAnnotation
@Service
public interface TreatmentService {

    Treatment create(Treatment treatment);

    Treatment save(Treatment treatment);

    Optional<Treatment> findById(Long treatmentId);

    List<Treatment> listAll();

    boolean isPresent(Long treatmentId);

    boolean isPresent(Treatment treatment);

    void deleteById(Long treatmentId);

}

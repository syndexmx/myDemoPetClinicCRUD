package com.github.syndexmx.demopetclinic.repository.reporitories;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.repository.entities.TreatmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@TemplatedAnnotation
@Repository
public interface TreatmentRepository extends JpaRepository<TreatmentEntity, Long> {
}

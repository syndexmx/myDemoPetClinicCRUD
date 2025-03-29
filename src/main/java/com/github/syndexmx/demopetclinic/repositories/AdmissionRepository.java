package com.github.syndexmx.demopetclinic.repositories;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.repositories.entities.AdmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@TemplatedAnnotation
@Repository
public interface AdmissionRepository extends JpaRepository<AdmissionEntity, Long> {
}

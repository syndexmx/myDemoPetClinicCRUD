package com.github.syndexmx.demopetclinic.repositories;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.repositories.entities.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@TemplatedAnnotation
@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
}

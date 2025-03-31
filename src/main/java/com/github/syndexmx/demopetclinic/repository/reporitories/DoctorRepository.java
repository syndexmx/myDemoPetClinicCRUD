package com.github.syndexmx.demopetclinic.repository.reporitories;

import com.github.syndexmx.demopetclinic.repository.entities.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
}

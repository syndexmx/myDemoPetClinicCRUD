package com.github.syndexmx.demopetclinic.repositories;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.repositories.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@TemplatedAnnotation
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}

package com.github.syndexmx.demopetclinic.services;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Owner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@TemplatedAnnotation
@Service
public interface OwnerService {

    Owner create(Owner owner);

    Owner save(Owner owner);

    Optional<Owner> findById(String ownerId);

    List<Owner> listAll();

    boolean isPresent(String ownerId);

    boolean isPresent(Owner owner);

    void deleteById(String ownerId);

}

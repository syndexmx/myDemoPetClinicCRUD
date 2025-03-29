package com.github.syndexmx.demopetclinic.services;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Pet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@TemplatedAnnotation
@Service
public interface PetService {

    Pet create(Pet pet);

    Pet save(Pet pet);

    Optional<Pet> findById(String petId);

    List<Pet> listAll();

    boolean isPresent(String petId);

    boolean isPresent(Pet pet);

    void deleteById(String petId);

}

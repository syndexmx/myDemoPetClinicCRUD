package com.github.syndexmx.demopetclinic.domain;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@TemplatedAnnotation
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Pet {

    private Long id;

    String name;
    String species;
    String breed;
    LocalDate birthDate;
    Integer weight; // grams
    String colour;
    PetSpecies petSpecies;

}

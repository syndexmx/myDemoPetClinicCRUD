package com.github.syndexmx.demopetclinic.repository.entities;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@TemplatedAnnotation
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "pets")
public class PetEntity {

    @Id
    private Long petId;

    @ManyToOne
    private OwnerEntity owner;

    private String name;
    private String species;
    private String breed;
    private LocalDate birthDate;
    private Integer weight; // grams
    private String colour;

    private String petSpecies;


}

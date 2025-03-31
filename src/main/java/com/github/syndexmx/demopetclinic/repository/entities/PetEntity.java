package com.github.syndexmx.demopetclinic.repository.entities;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@TemplatedAnnotation
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "pets")
public class PetEntity {

    @Id
    private Long id;

    private Long ownerId;

    private String name;
    private String species;
    private String breed;
    private LocalDate birthDate;
    private Integer weight; // grams
    private String colour;
    private String petSpecies;

    @OneToMany(cascade = CascadeType.PERSIST) // TODO: Check validity of cascade propagation
    private List<AdmissionEntity> admissionList;

}

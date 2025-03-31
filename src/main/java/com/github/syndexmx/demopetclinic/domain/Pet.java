package com.github.syndexmx.demopetclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Pet {

    private Long id;

    private Long ownerId;

    private String name;
    private String breed;
    private LocalDate birthDate;
    private Integer weight; // grams
    private String colour;
    private Species species;
    private Sex sex;

    private List<Long> admissionIdList;

}

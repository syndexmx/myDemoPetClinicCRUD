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
public class Address {

    private Long id;

    private String region;
    private String city;
    private String street;
    private Integer house;
    private String building;
    private Integer flat;

    private AddressFields addressFields;

}

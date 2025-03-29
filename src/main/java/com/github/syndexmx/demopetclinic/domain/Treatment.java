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
public class Treatment {

    private Long id;

    private String medicine;
    private Integer dose;
    private Integer times;
    private LocalDate fromDate;
    private LocalDate toDate;

    private TreatmentFields treatmentFields;

}

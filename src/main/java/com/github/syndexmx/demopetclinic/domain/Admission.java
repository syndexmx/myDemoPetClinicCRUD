package com.github.syndexmx.demopetclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Admission {

    private Long id;

    private Long petId;

    private Long doctorId;

    private LocalDate date;
    private String issue;
    private String inspection;
    private String diagnosis;

    private Long treatmentId;

    private AdmissionKind admissionKind;

}

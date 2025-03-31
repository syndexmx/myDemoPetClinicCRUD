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
public class Doctor {

    private Long id;

    private String name;
    private String phone;
    private LocalDate birthDate;
    private LocalDate startDate;

    private DoctorSpecialty doctorSpecialty;

}

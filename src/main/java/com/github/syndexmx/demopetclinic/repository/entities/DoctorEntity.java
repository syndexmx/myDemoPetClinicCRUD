package com.github.syndexmx.demopetclinic.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "doctors")
public class DoctorEntity {

    @Id
    private Long id;

    private String name;
    private String phone;
    private LocalDate birthDate;
    private LocalDate startDate;

    private String doctorFieldContent;


}

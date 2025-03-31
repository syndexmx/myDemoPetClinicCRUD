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
@Table(name = "admissions")
public class AdmissionEntity {

    @Id
    private Long id;

    private Long petId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private DoctorEntity doctor;

    private LocalDate date;
    private String issue;
    private String inspection;
    private String diagnosis;

    @OneToOne(cascade = CascadeType.DETACH)
    private TreatmentEntity treatment;

    private String admissionFieldContent;


}

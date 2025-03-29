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
@Table(name = "admissions")
public class AdmissionEntity {

    @Id
    private Long admissionId;

    private Long petId;
    private Long doctorId;
    private LocalDate date;
    private String issue;
    private String inspection;
    private String diagnosis;

    private String admissionFieldContent;


}

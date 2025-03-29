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
@Table(name = "treatments")
public class TreatmentEntity {

    @Id
    private Long treatmentId;

    private String medicine;
    private Integer dose;
    private Integer times;
    private LocalDate fromDate;
    private LocalDate toDate;

    private String treatmentFieldContent;


}

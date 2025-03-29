package com.github.syndexmx.demopetclinic.repositories.entities;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



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

    private String admissionFieldContent;


}

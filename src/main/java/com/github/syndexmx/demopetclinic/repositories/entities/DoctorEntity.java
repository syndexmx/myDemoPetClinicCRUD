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
@Table(name = "doctors")
public class DoctorEntity {

    @Id
    private Long doctorId;

    private String doctorFieldContent;


}

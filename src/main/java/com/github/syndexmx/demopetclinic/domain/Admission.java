package com.github.syndexmx.demopetclinic.domain;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@TemplatedAnnotation
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Admission {

    private Long id;

    private AdmissionFields admissionFields;

}

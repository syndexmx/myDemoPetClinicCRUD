package com.github.syndexmx.demopetclinic.domain;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;

@TemplatedAnnotation
public enum AdmissionKind {
    DEFAULT,
    PROFILAXIS,
    VACCINATION,
    INSPECTION,
    INJURY,
    MINOR_OPERATION,
    OPERATION,
    TREATMENT,
    HOSPITALIZATION;
}

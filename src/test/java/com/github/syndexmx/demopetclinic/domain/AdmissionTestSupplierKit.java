package com.github.syndexmx.demopetclinic.domain;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;

import java.time.LocalDate;
import java.util.Random;


@TemplatedAnnotation
public class AdmissionTestSupplierKit {

    private static Random random;

    private static Long id = random.nextLong();

    private static AdmissionFields GENERIC_FIELD_VALUE = AdmissionFields.DEFAULTVALUE;
    private static AdmissionFields GENERIC_STRING_MODIFIED = AdmissionFields.ALTERNATIVEVALUE;

    public static Admission getTestAdmission( ) {
        return Admission.builder()
                .id(id)
                .date(LocalDate.parse("2025-02-03"))
                .admissionFields(GENERIC_FIELD_VALUE)
                .build();
    }

    public static Admission getModifiedTestAdmission( ) {
        return Admission.builder()
                .id(id)
                .date(LocalDate.parse("2025-02-03"))
                .admissionFields(GENERIC_STRING_MODIFIED)
                .build();
    }

    private static Long NON_EXISTENT_Long = random.nextLong();
    private static AdmissionFields NON_EXISTENT_VALUE = AdmissionFields.OTHERVALUE;

    public static Admission getTestNonExistentAdmission( ) {
        return Admission.builder()
                .id(NON_EXISTENT_Long)
                .admissionFields(NON_EXISTENT_VALUE)
                .build();
    }

}

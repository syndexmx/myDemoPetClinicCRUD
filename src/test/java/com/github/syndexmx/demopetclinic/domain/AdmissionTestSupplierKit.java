package com.github.syndexmx.demopetclinic.domain;

import java.time.LocalDate;
import java.util.Random;

public class AdmissionTestSupplierKit {

    private static Random random;

    private static Long id = random.nextLong();

    private static AdmissionKind GENERIC_FIELD_VALUE = AdmissionKind.DEFAULT;
    private static AdmissionKind GENERIC_STRING_MODIFIED = AdmissionKind.PROFILAXIS;

    public static Admission getTestAdmission( ) {
        return Admission.builder()
                .id(id)
                .date(LocalDate.parse("2025-02-03"))
                .issue("Front leg trauma")
                .inspection("Improper bone movement")
                .diagnosis("Front left tibia fracture")
                .admissionKind(GENERIC_FIELD_VALUE)
                .build();
    }

    public static Admission getModifiedTestAdmission( ) {
        return Admission.builder()
                .id(id)
                .date(LocalDate.parse("2025-02-03"))
                .issue("Profilactic visit")
                .inspection("")
                .diagnosis("Anti-Rabies Vaccination")
                .admissionKind(GENERIC_STRING_MODIFIED)
                .build();
    }

    private static Long NON_EXISTENT_Long = random.nextLong();
    private static AdmissionKind NON_EXISTENT_VALUE = AdmissionKind.TREATMENT;

    public static Admission getTestNonExistentAdmission( ) {
        return Admission.builder()
                .id(NON_EXISTENT_Long)
                .admissionKind(NON_EXISTENT_VALUE)
                .build();
    }

}

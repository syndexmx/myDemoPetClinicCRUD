package com.github.syndexmx.demopetclinic.domain;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;

import java.time.LocalDate;
import java.util.Random;


@TemplatedAnnotation
public class TreatmentTestSupplierKit {

    private static Random random;

    private static Long id = random.nextLong();

    private static TreatmentFields GENERIC_FIELD_VALUE = TreatmentFields.IBUPROPHENE;
    private static TreatmentFields GENERIC_STRING_MODIFIED = TreatmentFields.ALTERNATIVEVALUE;

    public static Treatment getTestTreatment( ) {
        return Treatment.builder()
                .id(id)
                .medicine("Ibuprophene")
                .dose(750)
                .times(3)
                .fromDate(LocalDate.parse("2025-02-01"))
                .toDate(LocalDate.parse("2025-03-01"))
                .treatmentFields(GENERIC_FIELD_VALUE)
                .build();
    }

    public static Treatment getModifiedTestTreatment( ) {
        return Treatment.builder()
                .id(id)
                .medicine("Acetaminophene")
                .dose(1000)
                .times(2)
                .fromDate(LocalDate.parse("2025-03-01"))
                .toDate(LocalDate.parse("2025-03-15"))
                .treatmentFields(GENERIC_STRING_MODIFIED)
                .build();
    }

    private static Long NON_EXISTENT_Long = random.nextLong();
    private static TreatmentFields NON_EXISTENT_VALUE = TreatmentFields.OTHERVALUE;

    public static Treatment getTestNonExistentTreatment( ) {
        return Treatment.builder()
                .id(NON_EXISTENT_Long)
                .treatmentFields(NON_EXISTENT_VALUE)
                .build();
    }

}

package com.github.syndexmx.demopetclinic.domain;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;

import java.util.Random;


@TemplatedAnnotation
public class TreatmentTestSupplierKit {

    private static Random random;

    private static Long id = random.nextLong();

    private static TreatmentFields GENERIC_FIELD_VALUE = TreatmentFields.DEFAULTVALUE;
    private static TreatmentFields GENERIC_STRING_MODIFIED = TreatmentFields.ALTERNATIVEVALUE;

    public static Treatment getTestTreatment( ) {
        return Treatment.builder()
                .id(id)
                .treatmentFields(GENERIC_FIELD_VALUE)
                .build();
    }

    public static Treatment getModifiedTestTreatment( ) {
        return Treatment.builder()
                .id(id)
                .treatmentFields(GENERIC_STRING_MODIFIED)
                .build();
    }

    private static Long NON_EXISTENT_Long = random.nextLong();
    private static TreatmentFields NON_EXISTANT_VALUE = TreatmentFields.OTHERVALUE;

    public static Treatment getTestNonExistentTreatment( ) {
        return Treatment.builder()
                .id(NON_EXISTENT_Long)
                .treatmentFields(NON_EXISTANT_VALUE)
                .build();
    }

}

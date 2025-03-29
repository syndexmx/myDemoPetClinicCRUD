package com.github.syndexmx.demopetclinic.domain;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;

import java.util.Random;


@TemplatedAnnotation
public class PetTestSupplierKit {

    private static Random random;

    private static Long id = random.nextLong();

    private static PetFields GENERIC_FIELD_VALUE = PetFields.DEFAULTVALUE;
    private static PetFields GENERIC_STRING_MODIFIED = PetFields.ALTERNATIVEVALUE;

    public static Pet getTestPet( ) {
        return Pet.builder()
                .id(id)
                .petFields(GENERIC_FIELD_VALUE)
                .build();
    }

    public static Pet getModifiedTestPet( ) {
        return Pet.builder()
                .id(id)
                .petFields(GENERIC_STRING_MODIFIED)
                .build();
    }

    private static Long NON_EXISTENT_Long = random.nextLong();
    private static PetFields NON_EXISTANT_VALUE = PetFields.OTHERVALUE;

    public static Pet getTestNonExistentPet( ) {
        return Pet.builder()
                .id(NON_EXISTENT_Long)
                .petFields(NON_EXISTANT_VALUE)
                .build();
    }

}

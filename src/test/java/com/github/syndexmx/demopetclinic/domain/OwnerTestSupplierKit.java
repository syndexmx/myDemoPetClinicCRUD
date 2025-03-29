package com.github.syndexmx.demopetclinic.domain;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;

import java.util.Random;


@TemplatedAnnotation
public class OwnerTestSupplierKit {

    private static Random random;

    private static Long id = random.nextLong();

    private static OwnerFields GENERIC_FIELD_VALUE = OwnerFields.DEFAULTVALUE;
    private static OwnerFields GENERIC_STRING_MODIFIED = OwnerFields.ALTERNATIVEVALUE;

    public static Owner getTestOwner( ) {
        return Owner.builder()
                .id(id)
                .ownerFields(GENERIC_FIELD_VALUE)
                .build();
    }

    public static Owner getModifiedTestOwner( ) {
        return Owner.builder()
                .id(id)
                .ownerFields(GENERIC_STRING_MODIFIED)
                .build();
    }

    private static Long NON_EXISTENT_Long = random.nextLong();
    private static OwnerFields NON_EXISTANT_VALUE = OwnerFields.OTHERVALUE;

    public static Owner getTestNonExistentOwner( ) {
        return Owner.builder()
                .id(NON_EXISTENT_Long)
                .ownerFields(NON_EXISTANT_VALUE)
                .build();
    }

}

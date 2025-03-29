package com.github.syndexmx.demopetclinic.domain;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;

import java.util.Random;


@TemplatedAnnotation
public class AddressTestSupplierKit {

    private static Random random;

    private static Long id = random.nextLong();

    private static AddressFields GENERIC_FIELD_VALUE = AddressFields.DEFAULTVALUE;
    private static AddressFields GENERIC_STRING_MODIFIED = AddressFields.ALTERNATIVEVALUE;

    public static Address getTestAddress( ) {
        return Address.builder()
                .id(id)
                .addressFields(GENERIC_FIELD_VALUE)
                .build();
    }

    public static Address getModifiedTestAddress( ) {
        return Address.builder()
                .id(id)
                .addressFields(GENERIC_STRING_MODIFIED)
                .build();
    }

    private static Long NON_EXISTENT_Long = random.nextLong();
    private static AddressFields NON_EXISTANT_VALUE = AddressFields.OTHERVALUE;

    public static Address getTestNonExistentAddress( ) {
        return Address.builder()
                .id(NON_EXISTENT_Long)
                .addressFields(NON_EXISTANT_VALUE)
                .build();
    }

}

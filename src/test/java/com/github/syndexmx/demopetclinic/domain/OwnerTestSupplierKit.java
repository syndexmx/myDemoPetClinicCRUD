package com.github.syndexmx.demopetclinic.domain;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;

import java.util.ArrayList;
import java.util.Random;


@TemplatedAnnotation
public class OwnerTestSupplierKit {

    private static Random random;

    private static Long id = random.nextLong();

    private static OwnerFields GENERIC_FIELD_VALUE = OwnerFields.DEFAULTVALUE;
    private static OwnerFields GENERIC_STRING_MODIFIED = OwnerFields.ALTERNATIVEVALUE;

    public static Owner getTestOwner( ) {
        return Owner.builder()
                .name("John Smith")
                .phone("+1(800)01885476")
                .id(id)
                //.petIdList(new ArrayList<Pet>())
                .build();
    }

    public static Owner getModifiedTestOwner( ) {
        return Owner.builder()
                .id(id)
                .name("James Gold")
                .phone("+1(800)01997476")
                //.petIdList(new ArrayList<Pet>())
                .build();
    }

    private static Long NON_EXISTENT_Long = random.nextLong();
    private static OwnerFields NON_EXISTENT_VALUE = OwnerFields.OTHERVALUE;

    public static Owner getTestNonExistentOwner( ) {
        return Owner.builder()
                .id(NON_EXISTENT_Long)
                //.petIdList(new ArrayList<Pet>())
                .build();
    }

}

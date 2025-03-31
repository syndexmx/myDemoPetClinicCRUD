package com.github.syndexmx.demopetclinic.domain;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;

import java.util.ArrayList;
import java.util.Random;


@TemplatedAnnotation
public class OwnerTestSupplierKit {

    private static Random random;

    private static Long id = random.nextLong();

    public static Owner getTestOwner( ) {
        return Owner.builder()
                .name("John Smith")
                .phone("+1(800)01885476")
                .id(id)
                .petIdList(new ArrayList<Long>())
                .addressId(AddressTestSupplierKit.getTestAddress().getId())
                .build();
    }

    public static Owner getModifiedTestOwner( ) {
        return Owner.builder()
                .id(id)
                .name("James Gold")
                .phone("+1(800)01997476")
                .petIdList(new ArrayList<Long>())
                .addressId(AddressTestSupplierKit.getTestAddress().getId())
                .build();
    }

    private static Long NON_EXISTENT_Long = random.nextLong();

    public static Owner getTestNonExistentOwner( ) {
        return Owner.builder()
                .id(NON_EXISTENT_Long)
                //.petIdList(new ArrayList<Pet>())
                .build();
    }

}

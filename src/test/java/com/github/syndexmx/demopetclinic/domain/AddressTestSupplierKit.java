package com.github.syndexmx.demopetclinic.domain;

import java.util.Random;

public class AddressTestSupplierKit {

    private static Random random;

    private static Long id = random.nextLong();

    public static Address getTestAddress( ) {
        return Address.builder()
                .id(id)
                .region("Moscow region")
                .city("Moscow")
                .street("Lime")
                .house(18)
                .building("b")
                .flat(18)
                .build();
    }

    public static Address getModifiedTestAddress( ) {
        return Address.builder()
                .id(id)
                .region("Leningrad oblast")
                .city("Peregoff")
                .street("Lenin")
                .house(2)
                .building("")
                .flat(4)
                .build();
    }

    private static Long NON_EXISTENT_Long = random.nextLong();

    public static Address getTestNonExistentAddress( ) {
        return Address.builder()
                .id(NON_EXISTENT_Long)
                .build();
    }

}

package com.github.syndexmx.demopetclinic.domain;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;

import java.util.Random;


@TemplatedAnnotation
public class DoctorTestSupplierKit {

    private static Random random;

    private static Long id = random.nextLong();

    private static DoctorFields GENERIC_FIELD_VALUE = DoctorFields.DEFAULTVALUE;
    private static DoctorFields GENERIC_STRING_MODIFIED = DoctorFields.ALTERNATIVEVALUE;

    public static Doctor getTestDoctor( ) {
        return Doctor.builder()
                .id(id)
                .doctorFields(GENERIC_FIELD_VALUE)
                .build();
    }

    public static Doctor getModifiedTestDoctor( ) {
        return Doctor.builder()
                .id(id)
                .doctorFields(GENERIC_STRING_MODIFIED)
                .build();
    }

    private static Long NON_EXISTENT_Long = random.nextLong();
    private static DoctorFields NON_EXISTANT_VALUE = DoctorFields.OTHERVALUE;

    public static Doctor getTestNonExistentDoctor( ) {
        return Doctor.builder()
                .id(NON_EXISTENT_Long)
                .doctorFields(NON_EXISTANT_VALUE)
                .build();
    }

}

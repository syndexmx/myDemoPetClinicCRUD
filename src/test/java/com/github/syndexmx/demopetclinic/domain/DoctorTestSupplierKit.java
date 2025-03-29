package com.github.syndexmx.demopetclinic.domain;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;

import java.util.Random;


@TemplatedAnnotation
public class DoctorTestSupplierKit {

    private static Random random;

    private static Long id = random.nextLong();

    private static DoctorSpecialty GENERIC_FIELD_VALUE = DoctorSpecialty.DEFAULTVALUE;
    private static DoctorSpecialty GENERIC_STRING_MODIFIED = DoctorSpecialty.ALTERNATIVEVALUE;

    public static Doctor getTestDoctor( ) {
        return Doctor.builder()
                .id(id)
                .doctorSpecialty(GENERIC_FIELD_VALUE)
                .build();
    }

    public static Doctor getModifiedTestDoctor( ) {
        return Doctor.builder()
                .id(id)
                .doctorSpecialty(GENERIC_STRING_MODIFIED)
                .build();
    }

    private static Long NON_EXISTENT_Long = random.nextLong();
    private static DoctorSpecialty NON_EXISTANT_VALUE = DoctorSpecialty.OTHERVALUE;

    public static Doctor getTestNonExistentDoctor( ) {
        return Doctor.builder()
                .id(NON_EXISTENT_Long)
                .doctorSpecialty(NON_EXISTANT_VALUE)
                .build();
    }

}

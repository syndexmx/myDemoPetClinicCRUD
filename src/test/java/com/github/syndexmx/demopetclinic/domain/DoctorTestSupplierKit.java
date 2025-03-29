package com.github.syndexmx.demopetclinic.domain;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;

import java.time.LocalDate;
import java.util.Random;


@TemplatedAnnotation
public class DoctorTestSupplierKit {

    private static Random random;

    private static Long id = random.nextLong();

    private static DoctorSpecialty GENERIC_FIELD_VALUE = DoctorSpecialty.GP;
    private static DoctorSpecialty GENERIC_STRING_MODIFIED = DoctorSpecialty.SURGINE;

    public static Doctor getTestDoctor( ) {
        return Doctor.builder()
                .id(id)
                .name("Gregory House")
                .phone("+1(800)01235476")
                .birthDate(LocalDate.parse("1959-05-15"))
                .startDate(LocalDate.parse("1979-07-10"))
                .doctorSpecialty(GENERIC_FIELD_VALUE)
                .build();
    }

    public static Doctor getModifiedTestDoctor( ) {
        return Doctor.builder()
                .id(id)
                .name("Liza Cuddy")
                .phone("+1(800)9810293")
                .birthDate(LocalDate.parse("1968-01-14"))
                .startDate(LocalDate.parse("1980-08-12"))
                .doctorSpecialty(GENERIC_STRING_MODIFIED)
                .build();
    }

    private static Long NON_EXISTENT_Long = random.nextLong();
    private static DoctorSpecialty NON_EXISTENT_VALUE = DoctorSpecialty.ENDOCRINOLOGIST;

    public static Doctor getTestNonExistentDoctor( ) {
        return Doctor.builder()
                .id(NON_EXISTENT_Long)
                .doctorSpecialty(NON_EXISTENT_VALUE)
                .build();
    }

}

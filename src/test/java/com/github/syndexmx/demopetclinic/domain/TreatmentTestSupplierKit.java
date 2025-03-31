package com.github.syndexmx.demopetclinic.domain;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;

import java.time.LocalDate;
import java.util.Random;


@TemplatedAnnotation
public class TreatmentTestSupplierKit {

    private static Random random;

    private static Long id = random.nextLong();

    private static MedicineKind GENERIC_FIELD_VALUE = MedicineKind.IBUPROPHENE;
    private static MedicineKind GENERIC_STRING_MODIFIED = MedicineKind.ANTIBIOTICS;

    public static Treatment getTestTreatment( ) {
        return Treatment.builder()
                .id(id)
                .medicine("Ibuprophene")
                .dose(750)
                .times(3)
                .fromDate(LocalDate.parse("2025-02-01"))
                .toDate(LocalDate.parse("2025-03-01"))
                .medicineKind(GENERIC_FIELD_VALUE)
                .build();
    }

    public static Treatment getModifiedTestTreatment( ) {
        return Treatment.builder()
                .id(id)
                .medicine("Acetaminophene")
                .dose(1000)
                .times(2)
                .fromDate(LocalDate.parse("2025-03-01"))
                .toDate(LocalDate.parse("2025-03-15"))
                .medicineKind(GENERIC_STRING_MODIFIED)
                .build();
    }

    private static Long NON_EXISTENT_Long = random.nextLong();
    private static MedicineKind NON_EXISTENT_VALUE = MedicineKind.OTHERVALUE;

    public static Treatment getTestNonExistentTreatment( ) {
        return Treatment.builder()
                .id(NON_EXISTENT_Long)
                .medicineKind(NON_EXISTENT_VALUE)
                .build();
    }

}

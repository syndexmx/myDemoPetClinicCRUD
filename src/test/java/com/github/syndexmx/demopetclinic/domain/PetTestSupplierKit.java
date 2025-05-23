package com.github.syndexmx.demopetclinic.domain;

import java.time.LocalDate;
import java.util.Random;

public class PetTestSupplierKit {

    private static Random random;

    private static Long id = random.nextLong();

    public static Pet getTestPet( ) {
        return Pet.builder()
                .id(id)
                .breed("Английская")
                .birthDate(LocalDate.parse("2020-01-01"))
                .name("Котяра")
                .colour("Серый")
                .weight(2400)
                .species(Species.CAT)
                .sex(Sex.FEMALE)
                .build();
    }

    public static Pet getModifiedTestPet( ) {
        return Pet.builder()
                .id(id)
                .breed("Английский бульдог")
                .birthDate(LocalDate.parse("2022-03-03"))
                .name("Собакен")
                .colour("Серый")
                .weight(7650)
                .species(Species.DOG)
                .sex(Sex.MALE)
                .build();
    }


    public static Pet getTestNonExistentPet( ) {
        return Pet.builder()
                .id(random.nextLong())
                .build();
    }

}

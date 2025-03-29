package com.github.syndexmx.demopetclinic.controllers.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controllers.dtos.TreatmentDto;
import com.github.syndexmx.demopetclinic.domain.TreatmentFields;
import com.github.syndexmx.demopetclinic.domain.Treatment;

import java.util.Random;


@TemplatedAnnotation
public class TreatmentDtoMapper {

    public static TreatmentDto treatmentToTreatmentDto(Treatment treatment) {
        final TreatmentDto treatmentDto = TreatmentDto.builder()
                .id(treatment.getId())
                .treatmentFieldContent(treatment.getTreatmentFields().toString())
                .build();
        return treatmentDto;
    }

    public static Treatment treatmentDtoToTreatment(TreatmentDto treatmentDto) {
        Treatment treatment = Treatment.builder()
                .id(treatmentDto.getId())
                .treatmentFields(TreatmentFields.valueOf(treatmentDto.getTreatmentFieldContent()))
                .build();
        return treatment;
    }

    public static Treatment treatmentDtoNoIdToTreatment(TreatmentDto treatmentDto) {
        Random random = new Random();
        Treatment treatment = Treatment.builder()
                .id(random.nextLong())
                .treatmentFields(TreatmentFields.valueOf(treatmentDto.getTreatmentFieldContent()))
                .build();
        return treatment;
    }

}

package com.github.syndexmx.demopetclinic.repositories.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.TreatmentFields;
import com.github.syndexmx.demopetclinic.domain.Treatment;
import com.github.syndexmx.demopetclinic.repositories.entities.TreatmentEntity;

@TemplatedAnnotation
public class TreatmentEntityMapper {

    public static TreatmentEntity treatmentToTreatmentEntity(Treatment treatment) {
        final TreatmentEntity treatmentEntity = TreatmentEntity.builder()
                .treatmentId(treatment.getId())
                .treatmentFieldContent(treatment.getTreatmentFields().toString())
                .build();
        return treatmentEntity;
    }

    public static Treatment treatmentEntityToTreatment(TreatmentEntity treatmentEntity) {
        Treatment treatment = Treatment.builder()
                .id(treatmentEntity.getTreatmentId())
                .treatmentFields(TreatmentFields.valueOf(treatmentEntity.getTreatmentFieldContent()))
                .build();
        return treatment;
    }



}

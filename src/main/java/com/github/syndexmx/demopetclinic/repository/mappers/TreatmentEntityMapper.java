package com.github.syndexmx.demopetclinic.repository.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.TreatmentFields;
import com.github.syndexmx.demopetclinic.domain.Treatment;
import com.github.syndexmx.demopetclinic.repository.entities.TreatmentEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@TemplatedAnnotation
public class TreatmentEntityMapper {

    public TreatmentEntity treatmentToTreatmentEntity(Treatment treatment) {
        final TreatmentEntity treatmentEntity = TreatmentEntity.builder()
                .treatmentId(treatment.getId())
                .medicine(treatment.getMedicine())
                .dose(treatment.getDose())
                .times(treatment.getTimes())
                .fromDate(treatment.getFromDate())
                .toDate(treatment.getToDate())
                .treatmentFieldContent(treatment.getTreatmentFields().toString())
                .build();
        return treatmentEntity;
    }

    public Treatment treatmentEntityToTreatment(TreatmentEntity treatmentEntity) {
        Treatment treatment = Treatment.builder()
                .id(treatmentEntity.getTreatmentId())
                .medicine(treatmentEntity.getMedicine())
                .dose(treatmentEntity.getDose())
                .times(treatmentEntity.getTimes())
                .fromDate(treatmentEntity.getFromDate())
                .toDate(treatmentEntity.getToDate())
                .treatmentFields(TreatmentFields.valueOf(treatmentEntity.getTreatmentFieldContent()))
                .build();
        return treatment;
    }



}

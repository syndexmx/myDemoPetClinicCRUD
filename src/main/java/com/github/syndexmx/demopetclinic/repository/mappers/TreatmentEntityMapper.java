package com.github.syndexmx.demopetclinic.repository.mappers;

import com.github.syndexmx.demopetclinic.domain.MedicineKind;
import com.github.syndexmx.demopetclinic.domain.Treatment;
import com.github.syndexmx.demopetclinic.repository.entities.TreatmentEntity;
import org.springframework.stereotype.Service;

@Service
public class TreatmentEntityMapper {

    public TreatmentEntity treatmentToTreatmentEntity(Treatment treatment) {
        final TreatmentEntity treatmentEntity = TreatmentEntity.builder()
                .id(treatment.getId())
                .medicine(treatment.getMedicine())
                .dose(treatment.getDose())
                .times(treatment.getTimes())
                .fromDate(treatment.getFromDate())
                .toDate(treatment.getToDate())
                .generiq(treatment.getMedicineKind().toString())
                .build();
        return treatmentEntity;
    }

    public Treatment treatmentEntityToTreatment(TreatmentEntity treatmentEntity) {
        Treatment treatment = Treatment.builder()
                .id(treatmentEntity.getId())
                .medicine(treatmentEntity.getMedicine())
                .dose(treatmentEntity.getDose())
                .times(treatmentEntity.getTimes())
                .fromDate(treatmentEntity.getFromDate())
                .toDate(treatmentEntity.getToDate())
                .medicineKind(MedicineKind.valueOf(treatmentEntity.getGeneriq()))
                .build();
        return treatment;
    }



}

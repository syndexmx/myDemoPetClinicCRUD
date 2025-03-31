package com.github.syndexmx.demopetclinic.controller.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controller.dtos.TreatmentDto;
import com.github.syndexmx.demopetclinic.domain.MedicineKind;
import com.github.syndexmx.demopetclinic.domain.Treatment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
@TemplatedAnnotation
public class TreatmentDtoMapper {

    public TreatmentDto treatmentToTreatmentDto(Treatment treatment) {
        final TreatmentDto treatmentDto = TreatmentDto.builder()
                .id(treatment.getId())
                .medicine(treatment.getMedicine())
                .dose(treatment.getDose())
                .times(treatment.getTimes())
                .fromDate(treatment.getFromDate().toString())
                .toDate(treatment.getToDate().toString())
                .treatmentFieldContent(treatment.getMedicineKind().toString())
                .build();
        return treatmentDto;
    }

    public Treatment treatmentDtoToTreatment(TreatmentDto treatmentDto) {
        Treatment treatment = Treatment.builder()
                .id(treatmentDto.getId())
                .medicine(treatmentDto.getMedicine())
                .dose(treatmentDto.getDose())
                .times(treatmentDto.getTimes())
                .fromDate(LocalDate.parse(treatmentDto.getFromDate()))
                .toDate(LocalDate.parse(treatmentDto.getToDate()))
                .medicineKind(MedicineKind.valueOf(treatmentDto.getTreatmentFieldContent()))
                .build();
        return treatment;
    }

    public Treatment treatmentDtoNoIdToTreatment(TreatmentDto treatmentDto) {
        Random random = new Random();
        Treatment treatment = Treatment.builder()
                .id(random.nextLong())
                .medicine(treatmentDto.getMedicine())
                .dose(treatmentDto.getDose())
                .times(treatmentDto.getTimes())
                .fromDate(LocalDate.parse(treatmentDto.getFromDate()))
                .toDate(LocalDate.parse(treatmentDto.getToDate()))
                .medicineKind(MedicineKind.valueOf(treatmentDto.getTreatmentFieldContent())).build();
        return treatment;
    }

}

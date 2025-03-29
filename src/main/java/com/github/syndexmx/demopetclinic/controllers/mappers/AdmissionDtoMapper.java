package com.github.syndexmx.demopetclinic.controllers.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controllers.dtos.AdmissionDto;
import com.github.syndexmx.demopetclinic.domain.AdmissionFields;
import com.github.syndexmx.demopetclinic.domain.Admission;

import java.util.Random;


@TemplatedAnnotation
public class AdmissionDtoMapper {

    public static AdmissionDto admissionToAdmissionDto(Admission admission) {
        final AdmissionDto admissionDto = AdmissionDto.builder()
                .id(admission.getId())
                .admissionFieldContent(admission.getAdmissionFields().toString())
                .build();
        return admissionDto;
    }

    public static Admission admissionDtoToAdmission(AdmissionDto admissionDto) {
        Admission admission = Admission.builder()
                .id(admissionDto.getId())
                .admissionFields(AdmissionFields.valueOf(admissionDto.getAdmissionFieldContent()))
                .build();
        return admission;
    }

    public static Admission admissionDtoNoIdToAdmission(AdmissionDto admissionDto) {
        Random random = new Random();
        Admission admission = Admission.builder()
                .id(random.nextLong())
                .admissionFields(AdmissionFields.valueOf(admissionDto.getAdmissionFieldContent()))
                .build();
        return admission;
    }

}

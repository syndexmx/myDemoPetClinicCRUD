package com.github.syndexmx.demopetclinic.controller.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controller.dtos.AdmissionDto;
import com.github.syndexmx.demopetclinic.domain.AdmissionFields;
import com.github.syndexmx.demopetclinic.domain.Admission;

import java.time.LocalDate;
import java.util.Random;


@TemplatedAnnotation
public class AdmissionDtoMapper {

    public static AdmissionDto admissionToAdmissionDto(Admission admission) {
        final AdmissionDto admissionDto = AdmissionDto.builder()
                .id(admission.getId())
                .petId(admission.getPetId())
                .doctorId(admission.getDoctorId())
                .date(admission.getDate().toString())
                .admissionFieldContent(admission.getAdmissionFields().toString())
                .build();
        return admissionDto;
    }

    public static Admission admissionDtoToAdmission(AdmissionDto admissionDto) {
        Admission admission = Admission.builder()
                .id(admissionDto.getId())
                .petId(admissionDto.getPetId())
                .doctorId(admissionDto.getDoctorId())
                .date(LocalDate.parse(admissionDto.getDate()))
                .admissionFields(AdmissionFields.valueOf(admissionDto.getAdmissionFieldContent()))
                .build();
        return admission;
    }

    public static Admission admissionDtoNoIdToAdmission(AdmissionDto admissionDto) {
        Random random = new Random();
        Admission admission = Admission.builder()
                .id(random.nextLong())
                .petId(admissionDto.getPetId())
                .doctorId(admissionDto.getDoctorId())
                .date(LocalDate.parse(admissionDto.getDate()))
                .admissionFields(AdmissionFields.valueOf(admissionDto.getAdmissionFieldContent()))
                .build();
        return admission;
    }

}

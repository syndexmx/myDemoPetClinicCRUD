package com.github.syndexmx.demopetclinic.controller.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controller.dtos.AdmissionDto;
import com.github.syndexmx.demopetclinic.domain.AdmissionKind;
import com.github.syndexmx.demopetclinic.domain.Admission;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
@TemplatedAnnotation
public class AdmissionDtoMapper {

    public AdmissionDto admissionToAdmissionDto(Admission admission) {
        final AdmissionDto admissionDto = AdmissionDto.builder()
                .id(admission.getId())
                .petId(admission.getPetId())
                .doctorId(admission.getDoctorId())
                .date(admission.getDate().toString())
                .issue(admission.getIssue())
                .inspection(admission.getInspection())
                .diagnosis(admission.getDiagnosis())
                .treatment(admission.getTreatmentId())
                .admissionKind(admission.getAdmissionKind().toString())
                .build();
        return admissionDto;
    }

    public Admission admissionDtoToAdmission(AdmissionDto admissionDto) {
        Admission admission = Admission.builder()
                .id(admissionDto.getId())
                .petId(admissionDto.getPetId())
                .doctorId(admissionDto.getDoctorId())
                .date(LocalDate.parse(admissionDto.getDate()))
                .issue(admissionDto.getIssue())
                .inspection(admissionDto.getInspection())
                .diagnosis(admissionDto.getDiagnosis())
                .treatmentId(admissionDto.getTreatment())
                .admissionKind(AdmissionKind.valueOf(admissionDto.getAdmissionKind()))
                .build();
        return admission;
    }

    public Admission admissionDtoNoIdToAdmission(AdmissionDto admissionDto) {
        Random random = new Random();
        Admission admission = Admission.builder()
                .id(random.nextLong())
                .petId(admissionDto.getPetId())
                .doctorId(admissionDto.getDoctorId())
                .date(LocalDate.parse(admissionDto.getDate()))
                .issue(admissionDto.getIssue())
                .inspection(admissionDto.getInspection())
                .diagnosis(admissionDto.getDiagnosis())
                .treatmentId(admissionDto.getTreatment())
                .admissionKind(AdmissionKind.valueOf(admissionDto.getAdmissionKind()))
                .build();
        return admission;
    }

}

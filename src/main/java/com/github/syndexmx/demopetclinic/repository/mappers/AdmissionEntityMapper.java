package com.github.syndexmx.demopetclinic.repository.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.AdmissionFields;
import com.github.syndexmx.demopetclinic.domain.Admission;
import com.github.syndexmx.demopetclinic.repository.entities.AdmissionEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@TemplatedAnnotation
public class AdmissionEntityMapper {

    public AdmissionEntity admissionToAdmissionEntity(Admission admission) {
        final AdmissionEntity admissionEntity = AdmissionEntity.builder()
                .admissionId(admission.getId())
                .petId(admission.getPetId())
                .doctorId(admission.getDoctorId())
                .date(admission.getDate())
                .issue(admission.getIssue())
                .inspection(admission.getInspection())
                .diagnosis(admission.getDiagnosis())
                .admissionFieldContent(admission.getAdmissionFields().toString())
                .build();
        return admissionEntity;
    }

    public Admission admissionEntityToAdmission(AdmissionEntity admissionEntity) {
        Admission admission = Admission.builder()
                .id(admissionEntity.getAdmissionId())
                .petId(admissionEntity.getPetId())
                .doctorId(admissionEntity.getDoctorId())
                .date(admissionEntity.getDate())
                .issue(admissionEntity.getIssue())
                .inspection(admissionEntity.getInspection())
                .diagnosis(admissionEntity.getDiagnosis())
                .admissionFields(AdmissionFields.valueOf(admissionEntity.getAdmissionFieldContent()))
                .build();
        return admission;
    }



}

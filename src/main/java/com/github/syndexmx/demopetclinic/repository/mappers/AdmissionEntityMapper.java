package com.github.syndexmx.demopetclinic.repository.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.AdmissionFields;
import com.github.syndexmx.demopetclinic.domain.Admission;
import com.github.syndexmx.demopetclinic.repository.entities.AdmissionEntity;

import java.time.LocalDate;

@TemplatedAnnotation
public class AdmissionEntityMapper {

    public static AdmissionEntity admissionToAdmissionEntity(Admission admission) {
        final AdmissionEntity admissionEntity = AdmissionEntity.builder()
                .admissionId(admission.getId())
                .petId(admission.getPetId())
                .doctorId(admission.getDoctorId())
                .date(admission.getDate())
                .admissionFieldContent(admission.getAdmissionFields().toString())
                .build();
        return admissionEntity;
    }

    public static Admission admissionEntityToAdmission(AdmissionEntity admissionEntity) {
        Admission admission = Admission.builder()
                .id(admissionEntity.getAdmissionId())
                .petId(admissionEntity.getPetId())
                .doctorId(admissionEntity.getDoctorId())
                .date(admissionEntity.getDate())
                .admissionFields(AdmissionFields.valueOf(admissionEntity.getAdmissionFieldContent()))
                .build();
        return admission;
    }



}

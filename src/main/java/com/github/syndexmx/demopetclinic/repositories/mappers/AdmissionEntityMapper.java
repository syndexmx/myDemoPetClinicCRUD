package com.github.syndexmx.demopetclinic.repositories.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.AdmissionFields;
import com.github.syndexmx.demopetclinic.domain.Admission;
import com.github.syndexmx.demopetclinic.repositories.entities.AdmissionEntity;

@TemplatedAnnotation
public class AdmissionEntityMapper {

    public static AdmissionEntity admissionToAdmissionEntity(Admission admission) {
        final AdmissionEntity admissionEntity = AdmissionEntity.builder()
                .admissionId(admission.getId())
                .admissionFieldContent(admission.getAdmissionFields().toString())
                .build();
        return admissionEntity;
    }

    public static Admission admissionEntityToAdmission(AdmissionEntity admissionEntity) {
        Admission admission = Admission.builder()
                .id(admissionEntity.getAdmissionId())
                .admissionFields(AdmissionFields.valueOf(admissionEntity.getAdmissionFieldContent()))
                .build();
        return admission;
    }



}

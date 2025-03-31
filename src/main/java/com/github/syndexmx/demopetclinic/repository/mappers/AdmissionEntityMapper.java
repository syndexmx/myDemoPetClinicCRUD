package com.github.syndexmx.demopetclinic.repository.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.AdmissionKind;
import com.github.syndexmx.demopetclinic.domain.Admission;
import com.github.syndexmx.demopetclinic.repository.entities.AdmissionEntity;
import com.github.syndexmx.demopetclinic.services.DoctorService;
import com.github.syndexmx.demopetclinic.services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@TemplatedAnnotation
public class AdmissionEntityMapper {

    @Autowired
    @Lazy
    DoctorService doctorService;

    @Autowired
    @Lazy
    DoctorEntityMapper doctorEntityMapper;

    @Autowired
    @Lazy
    TreatmentService treatmentService;

    @Autowired
    @Lazy
    TreatmentEntityMapper treatmentEntityMapper;


    public AdmissionEntity admissionToAdmissionEntity(Admission admission) {
        final AdmissionEntity admissionEntity = AdmissionEntity.builder()
                .id(admission.getId())
                .petId(admission.getPetId())
                .doctor(doctorEntityMapper.doctorToDoctorEntity(
                        doctorService.findById(admission.getDoctorId()).orElseThrow()))
                .date(admission.getDate())
                .issue(admission.getIssue())
                .inspection(admission.getInspection())
                .diagnosis(admission.getDiagnosis())
                .treatment(treatmentEntityMapper.treatmentToTreatmentEntity(
                        treatmentService.findById(admission.getTreatmentId()).orElseThrow()))
                .admissionFieldContent(admission.getAdmissionKind().toString())
                .build();
        return admissionEntity;
    }

    public Admission admissionEntityToAdmission(AdmissionEntity admissionEntity) {
        Admission admission = Admission.builder()
                .id(admissionEntity.getId())
                .petId(admissionEntity.getPetId())
                .doctorId(admissionEntity.getDoctor().getId())
                .date(admissionEntity.getDate())
                .issue(admissionEntity.getIssue())
                .inspection(admissionEntity.getInspection())
                .diagnosis(admissionEntity.getDiagnosis())
                .treatmentId(admissionEntity.getTreatment().getId())
                .admissionKind(AdmissionKind.valueOf(admissionEntity.getAdmissionFieldContent()))
                .build();
        return admission;
    }

}

package com.github.syndexmx.demopetclinic.repository.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.DoctorFields;
import com.github.syndexmx.demopetclinic.domain.Doctor;
import com.github.syndexmx.demopetclinic.repository.entities.DoctorEntity;

@TemplatedAnnotation
public class DoctorEntityMapper {

    public static DoctorEntity doctorToDoctorEntity(Doctor doctor) {
        final DoctorEntity doctorEntity = DoctorEntity.builder()
                .doctorId(doctor.getId())
                .doctorFieldContent(doctor.getDoctorFields().toString())
                .build();
        return doctorEntity;
    }

    public static Doctor doctorEntityToDoctor(DoctorEntity doctorEntity) {
        Doctor doctor = Doctor.builder()
                .id(doctorEntity.getDoctorId())
                .doctorFields(DoctorFields.valueOf(doctorEntity.getDoctorFieldContent()))
                .build();
        return doctor;
    }



}

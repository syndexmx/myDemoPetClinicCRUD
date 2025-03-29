package com.github.syndexmx.demopetclinic.repository.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.DoctorSpecialty;
import com.github.syndexmx.demopetclinic.domain.Doctor;
import com.github.syndexmx.demopetclinic.repository.entities.DoctorEntity;

@TemplatedAnnotation
public class DoctorEntityMapper {

    public static DoctorEntity doctorToDoctorEntity(Doctor doctor) {
        final DoctorEntity doctorEntity = DoctorEntity.builder()
                .doctorId(doctor.getId())
                .name(doctor.getName())
                .phone(doctor.getPhone())
                .birthDate(doctor.getBirthDate())
                .startDate(doctor.getStartDate())
                .doctorFieldContent(doctor.getDoctorSpecialty().toString())
                .build();
        return doctorEntity;
    }

    public static Doctor doctorEntityToDoctor(DoctorEntity doctorEntity) {
        Doctor doctor = Doctor.builder()
                .id(doctorEntity.getDoctorId())
                .name(doctorEntity.getName())
                .phone(doctorEntity.getPhone())
                .birthDate(doctorEntity.getBirthDate())
                .startDate(doctorEntity.getStartDate())
                .doctorSpecialty(DoctorSpecialty.valueOf(doctorEntity.getDoctorFieldContent()))
                .build();
        return doctor;
    }



}

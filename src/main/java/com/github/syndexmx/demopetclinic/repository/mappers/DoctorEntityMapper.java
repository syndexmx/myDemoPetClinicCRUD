package com.github.syndexmx.demopetclinic.repository.mappers;

import com.github.syndexmx.demopetclinic.domain.DoctorSpecialty;
import com.github.syndexmx.demopetclinic.domain.Doctor;
import com.github.syndexmx.demopetclinic.repository.entities.DoctorEntity;
import org.springframework.stereotype.Service;

@Service
public class DoctorEntityMapper {

    public DoctorEntity doctorToDoctorEntity(Doctor doctor) {
        final DoctorEntity doctorEntity = DoctorEntity.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .phone(doctor.getPhone())
                .birthDate(doctor.getBirthDate())
                .startDate(doctor.getStartDate())
                .doctorFieldContent(doctor.getDoctorSpecialty().toString())
                .build();
        return doctorEntity;
    }

    public Doctor doctorEntityToDoctor(DoctorEntity doctorEntity) {
        Doctor doctor = Doctor.builder()
                .id(doctorEntity.getId())
                .name(doctorEntity.getName())
                .phone(doctorEntity.getPhone())
                .birthDate(doctorEntity.getBirthDate())
                .startDate(doctorEntity.getStartDate())
                .doctorSpecialty(DoctorSpecialty.valueOf(doctorEntity.getDoctorFieldContent()))
                .build();
        return doctor;
    }



}

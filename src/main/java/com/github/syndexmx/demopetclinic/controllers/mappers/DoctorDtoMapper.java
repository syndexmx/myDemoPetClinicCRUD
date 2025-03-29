package com.github.syndexmx.demopetclinic.controllers.mappers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controllers.dtos.DoctorDto;
import com.github.syndexmx.demopetclinic.domain.DoctorFields;
import com.github.syndexmx.demopetclinic.domain.Doctor;

import java.util.Random;


@TemplatedAnnotation
public class DoctorDtoMapper {

    public static DoctorDto doctorToDoctorDto(Doctor doctor) {
        final DoctorDto doctorDto = DoctorDto.builder()
                .id(doctor.getId())
                .doctorFieldContent(doctor.getDoctorFields().toString())
                .build();
        return doctorDto;
    }

    public static Doctor doctorDtoToDoctor(DoctorDto doctorDto) {
        Doctor doctor = Doctor.builder()
                .id(doctorDto.getId())
                .doctorFields(DoctorFields.valueOf(doctorDto.getDoctorFieldContent()))
                .build();
        return doctor;
    }

    public static Doctor doctorDtoNoIdToDoctor(DoctorDto doctorDto) {
        Random random = new Random();
        Doctor doctor = Doctor.builder()
                .id(random.nextLong())
                .doctorFields(DoctorFields.valueOf(doctorDto.getDoctorFieldContent()))
                .build();
        return doctor;
    }

}

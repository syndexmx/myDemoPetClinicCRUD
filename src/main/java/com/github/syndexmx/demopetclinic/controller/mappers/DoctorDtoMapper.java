package com.github.syndexmx.demopetclinic.controller.mappers;

import com.github.syndexmx.demopetclinic.controller.dtos.DoctorDto;
import com.github.syndexmx.demopetclinic.domain.DoctorSpecialty;
import com.github.syndexmx.demopetclinic.domain.Doctor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class DoctorDtoMapper {

    public DoctorDto doctorToDoctorDto(Doctor doctor) {
        final DoctorDto doctorDto = DoctorDto.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .phone(doctor.getPhone())
                .startDate(doctor.getStartDate().toString())
                .birthDate(doctor.getBirthDate().toString())
                .doctorSpecialty(doctor.getDoctorSpecialty().toString())
                .build();
        return doctorDto;
    }

    public Doctor doctorDtoToDoctor(DoctorDto doctorDto) {
        Doctor doctor = Doctor.builder()
                .id(doctorDto.getId())
                .name(doctorDto.getName())
                .phone(doctorDto.getPhone())
                .startDate(LocalDate.parse(doctorDto.getStartDate()))
                .birthDate(LocalDate.parse(doctorDto.getBirthDate()))
                .doctorSpecialty(DoctorSpecialty.valueOf(doctorDto.getDoctorSpecialty()))
                .build();
        return doctor;
    }

    public Doctor doctorDtoNoIdToDoctor(DoctorDto doctorDto) {
        Random random = new Random();
        Doctor doctor = Doctor.builder()
                .id(random.nextLong())
                .name(doctorDto.getName())
                .phone(doctorDto.getPhone())
                .startDate(LocalDate.parse(doctorDto.getStartDate()))
                .birthDate(LocalDate.parse(doctorDto.getBirthDate()))
                .doctorSpecialty(DoctorSpecialty.valueOf(doctorDto.getDoctorSpecialty()))
                .build();
        return doctor;
    }

}

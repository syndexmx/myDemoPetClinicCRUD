package com.github.syndexmx.demopetclinic.controllers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Doctor;
import com.github.syndexmx.demopetclinic.controllers.dtos.DoctorDto;
import com.github.syndexmx.demopetclinic.services.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.github.syndexmx.demopetclinic.controllers.mappers.DoctorDtoMapper.*;

@TemplatedAnnotation
@RestController
@Tag(name = "Doctor", description = "API Доктор")
@Slf4j
public class DoctorController {

    private final String ROOT_API_PATH = "/api/v0/doctors";

    private final DoctorService doctorService;

    @Autowired
    private DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(ROOT_API_PATH)
    @Operation(summary = "Доктор:добавить",
            description = "Создание нового объекта Доктор. id присваивается системой")
    public ResponseEntity<DoctorDto> create(@RequestBody final DoctorDto doctorDto) {
        log.info("POST " + ROOT_API_PATH + " \n" + doctorDto);
        final Doctor doctor = doctorDtoNoIdToDoctor(doctorDto);
        final ResponseEntity<DoctorDto> responseEntity = new ResponseEntity<> (
                doctorToDoctorDto(doctorService.create(doctor)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH + "/{doctorId}")
    @Operation(summary = "Доктор:получить по id",
            description = "Получить существующий объект Доктор")
    public ResponseEntity<DoctorDto> retrieve(@PathVariable String doctorId) {
        final Optional<Doctor> foundDoctor = doctorService.findById(doctorId);
        if (foundDoctor.isEmpty()) {
            return new ResponseEntity<DoctorDto>(HttpStatus.NOT_FOUND);
        } else {
            final DoctorDto doctorDto = doctorToDoctorDto(foundDoctor.get());
            return new ResponseEntity<DoctorDto>(doctorDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    @Operation(summary = "Доктор:получить весь список",
            description = "Получить все существующие в базе объекты Доктор")
    public ResponseEntity<List<DoctorDto>> retrieveAll() {
        final List<Doctor> listFound = doctorService.listAll();
        final List<DoctorDto> listFoundDtos = listFound.stream()
                .map(doctor -> doctorToDoctorDto(doctor)).toList();
        final ResponseEntity<List<DoctorDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH + "/{doctorId}")
    @Operation(summary = "Доктор:обновить объект по id",
            description = "Обновить существующий в базе объект Доктор")
    public ResponseEntity<DoctorDto> update(@RequestBody final DoctorDto doctorDto) {
        log.info("PUT " + ROOT_API_PATH + " \n" + doctorDto);
        final Doctor doctor = doctorDtoToDoctor(doctorDto);
        if (!doctorService.isPresent(doctor)) {
            final ResponseEntity<DoctorDto> responseEntity = new ResponseEntity<> (
                    doctorToDoctorDto(doctorService.save(doctor)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<DoctorDto> responseEntity = new ResponseEntity<> (
                doctorToDoctorDto(doctorService.save(doctor)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH + "/{doctorId}")
    @Operation(summary = "Доктор:удалить объект по id",
            description = "Удалить существующий в базе объект Доктор")
    public ResponseEntity deleteById(@PathVariable String doctorId) {
        log.info("DELETE " + ROOT_API_PATH + " \n" + doctorId);
        doctorService.deleteById(doctorId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

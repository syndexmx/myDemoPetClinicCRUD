package com.github.syndexmx.demopetclinic.controller.controllers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controller.mappers.AdmissionDtoMapper;
import com.github.syndexmx.demopetclinic.domain.Admission;
import com.github.syndexmx.demopetclinic.controller.dtos.AdmissionDto;
import com.github.syndexmx.demopetclinic.services.AdmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.github.syndexmx.demopetclinic.controller.mappers.AdmissionDtoMapper.*;

@TemplatedAnnotation
@RestController
@Tag(name = "Admission", description = "API Визит")
@Slf4j
public class AdmissionController {

    private final String ROOT_API_PATH = "/api/v0/admissions";

    private final AdmissionService admissionService;
    private final AdmissionDtoMapper addmissionDtoMapper;

    @Autowired
    private AdmissionController(AdmissionService admissionService, AdmissionDtoMapper addmissionDtoMapper) {
        this.admissionService = admissionService;
        this.addmissionDtoMapper = addmissionDtoMapper;
    }

    @PostMapping(ROOT_API_PATH)
    @Operation(summary = "Визит:добавить",
            description = "Создание нового объекта Визит. id присваивается системой")
    public ResponseEntity<AdmissionDto> create(@RequestBody final AdmissionDto admissionDto) {
        log.info("POST " + ROOT_API_PATH + " \n" + admissionDto);
        final Admission admission = addmissionDtoMapper.admissionDtoNoIdToAdmission(admissionDto);
        final ResponseEntity<AdmissionDto> responseEntity = new ResponseEntity<> (
                addmissionDtoMapper.admissionToAdmissionDto(admissionService.create(admission)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH +"/{admissionId}")
    @Operation(summary = "Визит:получить по id",
            description = "Получить существующий объект Визит")
    public ResponseEntity<AdmissionDto> retrieve(@PathVariable String admissionId) {
        final Optional<Admission> foundAdmission = admissionService.findById(admissionId);
        if (foundAdmission.isEmpty()) {
            return new ResponseEntity<AdmissionDto>(HttpStatus.NOT_FOUND);
        } else {
            final AdmissionDto admissionDto = addmissionDtoMapper.admissionToAdmissionDto(foundAdmission.get());
            return new ResponseEntity<AdmissionDto>(admissionDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    @Operation(summary = "Визит:получить весь список",
            description = "Получить все существующие в базе объекты Визит")
    public ResponseEntity<List<AdmissionDto>> retrieveAll() {
        final List<Admission> listFound = admissionService.listAll();
        final List<AdmissionDto> listFoundDtos = listFound.stream()
                .map(admission -> addmissionDtoMapper.admissionToAdmissionDto(admission)).toList();
        final ResponseEntity<List<AdmissionDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH +"/{admissionId}")
    @Operation(summary = "Визит:обновить объект по id",
            description = "Обновить существующий в базе объект Визит")
    public ResponseEntity<AdmissionDto> update(@RequestBody final AdmissionDto admissionDto) {
        log.info("PUT " + ROOT_API_PATH + " \n" + admissionDto);
        final Admission admission = addmissionDtoMapper.admissionDtoToAdmission(admissionDto);
        if (!admissionService.isPresent(admission)) {
            final ResponseEntity<AdmissionDto> responseEntity = new ResponseEntity<> (
                    addmissionDtoMapper.admissionToAdmissionDto(admissionService.save(admission)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<AdmissionDto> responseEntity = new ResponseEntity<> (
                addmissionDtoMapper.admissionToAdmissionDto(admissionService.save(admission)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH + "/{admissionId}")
    @Operation(summary = "Визит:удалить объект по id",
            description = "Удалить существующий в базе объект Визит")
    public ResponseEntity deleteById(@PathVariable String admissionId) {
        log.info("DELETE " + ROOT_API_PATH + admissionId);
        admissionService.deleteById(admissionId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

package com.github.syndexmx.demopetclinic.controller.controllers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controller.mappers.TreatmentDtoMapper;
import com.github.syndexmx.demopetclinic.domain.Treatment;
import com.github.syndexmx.demopetclinic.controller.dtos.TreatmentDto;
import com.github.syndexmx.demopetclinic.services.TreatmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.github.syndexmx.demopetclinic.controller.mappers.TreatmentDtoMapper.*;

@TemplatedAnnotation
@RestController
@Tag(name = "Treatment", description = "API Лечение")
@Slf4j
public class TreatmentController {

    private final String ROOT_API_PATH = "/api/v0/treatments";

    private final TreatmentService treatmentService;
    private final TreatmentDtoMapper treatmentDtoMapper;

    @Autowired
    private TreatmentController(TreatmentService treatmentService, TreatmentDtoMapper treatmentDtoMapper) {
        this.treatmentService = treatmentService;
        this.treatmentDtoMapper = treatmentDtoMapper;
    }

    @PostMapping(ROOT_API_PATH)
    @Operation(summary = "Лечение:добавить",
            description = "Создание нового объекта Лечение. id присваивается системой")
    public ResponseEntity<TreatmentDto> create(@RequestBody final TreatmentDto treatmentDto) {
        log.info("POST " + ROOT_API_PATH + " \n" + treatmentDto);
        final Treatment treatment = treatmentDtoMapper.treatmentDtoNoIdToTreatment(treatmentDto);
        final ResponseEntity<TreatmentDto> responseEntity = new ResponseEntity<> (
                treatmentDtoMapper.treatmentToTreatmentDto(treatmentService.create(treatment)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH + "/{treatmentId}")
    @Operation(summary = "Лечение:получить по id",
            description = "Получить существующий объект Лечение")
    public ResponseEntity<TreatmentDto> retrieve(@PathVariable String treatmentId) {
        final Optional<Treatment> foundTreatment = treatmentService.findById(treatmentId);
        if (foundTreatment.isEmpty()) {
            return new ResponseEntity<TreatmentDto>(HttpStatus.NOT_FOUND);
        } else {
            final TreatmentDto treatmentDto = treatmentDtoMapper.treatmentToTreatmentDto(foundTreatment.get());
            return new ResponseEntity<TreatmentDto>(treatmentDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    @Operation(summary = "Лечение:получить весь список",
            description = "Получить все существующие в базе объекты Лечение")
    public ResponseEntity<List<TreatmentDto>> retrieveAll() {
        final List<Treatment> listFound = treatmentService.listAll();
        final List<TreatmentDto> listFoundDtos = listFound.stream()
                .map(treatment -> treatmentDtoMapper.treatmentToTreatmentDto(treatment)).toList();
        final ResponseEntity<List<TreatmentDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH + "/{treatmentId}")
    @Operation(summary = "Лечение:обновить объект по id",
            description = "Обновить существующий в базе объект Лечение")
    public ResponseEntity<TreatmentDto> update(@RequestBody final TreatmentDto treatmentDto) {
        final Treatment treatment = treatmentDtoMapper.treatmentDtoToTreatment(treatmentDto);
        if (!treatmentService.isPresent(treatment)) {
            final ResponseEntity<TreatmentDto> responseEntity = new ResponseEntity<> (
                    treatmentDtoMapper.treatmentToTreatmentDto(treatmentService.save(treatment)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<TreatmentDto> responseEntity = new ResponseEntity<> (
                treatmentDtoMapper.treatmentToTreatmentDto(treatmentService.save(treatment)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH + "/{treatmentId}")
    @Operation(summary = "Лечение:удалить объект по id",
            description = "Удалить существующий в базе объект Лечение")
    public ResponseEntity deleteById(@PathVariable String treatmentId) {
        treatmentService.deleteById(treatmentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

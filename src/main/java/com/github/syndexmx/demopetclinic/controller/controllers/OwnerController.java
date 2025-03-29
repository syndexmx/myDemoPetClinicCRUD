package com.github.syndexmx.demopetclinic.controller.controllers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Owner;
import com.github.syndexmx.demopetclinic.controller.dtos.OwnerDto;
import com.github.syndexmx.demopetclinic.services.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.github.syndexmx.demopetclinic.controller.mappers.OwnerDtoMapper.*;

@TemplatedAnnotation
@RestController
@Tag(name = "Owner", description = "API Хозяин")
@Slf4j
public class OwnerController {

    private final String ROOT_API_PATH = "/api/v0/owners";

    private final OwnerService ownerService;

    @Autowired
    private OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping(ROOT_API_PATH)
    @Operation(summary = "Хозяин:добавить",
            description = "Создание нового объекта Хозяин. id присваивается системой")
    public ResponseEntity<OwnerDto> create(@RequestBody final OwnerDto ownerDto) {
        log.info("POST " + ROOT_API_PATH + " \n" + ownerDto);
        final Owner owner = ownerDtoNoIdToOwner(ownerDto);
        final ResponseEntity<OwnerDto> responseEntity = new ResponseEntity<> (
                ownerToOwnerDto(ownerService.create(owner)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH + "/{ownerId}")
    @Operation(summary = "Хозяин:получить по id",
            description = "Получить существующий объект Хозяин")
    public ResponseEntity<OwnerDto> retrieve(@PathVariable String ownerId) {
        final Optional<Owner> foundOwner = ownerService.findById(ownerId);
        if (foundOwner.isEmpty()) {
            return new ResponseEntity<OwnerDto>(HttpStatus.NOT_FOUND);
        } else {
            final OwnerDto ownerDto = ownerToOwnerDto(foundOwner.get());
            return new ResponseEntity<OwnerDto>(ownerDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    @Operation(summary = "Хозяин:получить весь список",
            description = "Получить все существующие в базе объекты Хозяин")
    public ResponseEntity<List<OwnerDto>> retrieveAll() {
        final List<Owner> listFound = ownerService.listAll();
        final List<OwnerDto> listFoundDtos = listFound.stream()
                .map(owner -> ownerToOwnerDto(owner)).toList();
        final ResponseEntity<List<OwnerDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH + "/{ownerId}")
    @Operation(summary = "Хозяин:обновить объект по id",
            description = "Обновить существующий в базе объект Хозяин")
    public ResponseEntity<OwnerDto> update(@RequestBody final OwnerDto ownerDto) {
        log.info("PUT " + ROOT_API_PATH + " \n" + ownerDto);
        final Owner owner = ownerDtoToOwner(ownerDto);
        if (!ownerService.isPresent(owner)) {
            final ResponseEntity<OwnerDto> responseEntity = new ResponseEntity<> (
                    ownerToOwnerDto(ownerService.save(owner)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<OwnerDto> responseEntity = new ResponseEntity<> (
                ownerToOwnerDto(ownerService.save(owner)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH + "/{ownerId}")
    @Operation(summary = "Хозяин:удалить объект по id",
            description = "Удалить существующий в базе объект Хозяин")
    public ResponseEntity deleteById(@PathVariable String ownerId) {
        log.info("POST " + ROOT_API_PATH + " \n" + ownerId);
        ownerService.deleteById(ownerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

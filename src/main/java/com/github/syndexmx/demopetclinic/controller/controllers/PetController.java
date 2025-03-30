package com.github.syndexmx.demopetclinic.controller.controllers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.controller.mappers.PetDtoMapper;
import com.github.syndexmx.demopetclinic.domain.Pet;
import com.github.syndexmx.demopetclinic.controller.dtos.PetDto;
import com.github.syndexmx.demopetclinic.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@TemplatedAnnotation
@RestController
@Tag(name = "Pet", description = "API Животное")
@Slf4j
public class PetController {

    private final String ROOT_API_PATH = "/api/v0/pets";

    private final PetService petService;
    private final PetDtoMapper petDtoMapper;

    @Autowired
    private PetController(PetService petService, PetDtoMapper petDtoMapper) {
        this.petService = petService;
        this.petDtoMapper = petDtoMapper;
    }

    @PostMapping(ROOT_API_PATH)
    @Operation(summary = "Животное:добавить",
            description = "Создание нового объекта Животное. id присваивается системой")
    public ResponseEntity<PetDto> create(@RequestBody final PetDto petDto) {
        log.info("POST " + ROOT_API_PATH + " \n" + petDto);
        final Pet pet = petDtoMapper.petDtoNoIdToPet(petDto);
        final ResponseEntity<PetDto> responseEntity = new ResponseEntity<> (
                petDtoMapper.petToPetDto(petService.create(pet)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH + "/{petId}")
    @Operation(summary = "Животное:получить по id",
            description = "Получить существующий объект Животное")
    public ResponseEntity<PetDto> retrieve(@PathVariable Long petId) {
        final Optional<Pet> foundPet = petService.findById(petId);
        if (foundPet.isEmpty()) {
            return new ResponseEntity<PetDto>(HttpStatus.NOT_FOUND);
        } else {
            final PetDto petDto = petDtoMapper.petToPetDto(foundPet.get());
            return new ResponseEntity<PetDto>(petDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    @Operation(summary = "Животное:получить весь список",
            description = "Получить все существующие в базе объекты Животное")
    public ResponseEntity<List<PetDto>> retrieveAll() {
        final List<Pet> listFound = petService.listAll();
        final List<PetDto> listFoundDtos = listFound.stream()
                .map(pet -> petDtoMapper.petToPetDto(pet)).toList();
        final ResponseEntity<List<PetDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH + "/{petId}")
    @Operation(summary = "Животное:обновить объект по id",
            description = "Обновить существующий в базе объект Животное")
    public ResponseEntity<PetDto> update(@RequestBody final PetDto petDto) {
        log.info("PUT " + ROOT_API_PATH + " \n" + petDto);
        final Pet pet = petDtoMapper.petDtoToPet(petDto);
        if (!petService.isPresent(pet)) {
            final ResponseEntity<PetDto> responseEntity = new ResponseEntity<> (
                    petDtoMapper.petToPetDto(petService.save(pet)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<PetDto> responseEntity = new ResponseEntity<> (
                petDtoMapper.petToPetDto(petService.save(pet)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH + "/{petId}")
    @Operation(summary = "Животное:удалить объект по id",
            description = "Удалить существующий в базе объект Животное")
    public ResponseEntity deleteById(@PathVariable Long petId) {
        log.info("POST " + ROOT_API_PATH + " \n" + petId);
        petService.deleteById(petId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

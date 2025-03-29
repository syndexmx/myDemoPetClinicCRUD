package com.github.syndexmx.demopetclinic.controllers;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Pet;
import com.github.syndexmx.demopetclinic.controllers.dtos.PetDto;
import com.github.syndexmx.demopetclinic.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.github.syndexmx.demopetclinic.controllers.mappers.PetDtoMapper.*;

@TemplatedAnnotation
@RestController
@Tag(name = "Pet", description = "API Животное")
public class PetController {

    private final String ROOT_API_PATH = "/api/v0/pets";

    private final PetService petService;

    @Autowired
    private PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping(ROOT_API_PATH)
    @Operation(summary = "Животное:добавить",
            description = "Создание нового объекта Животное. id присваивается системой")
    public ResponseEntity<PetDto> create(@RequestBody final PetDto petDto) {
        final Pet pet = petDtoNoIdToPet(petDto);
        final ResponseEntity<PetDto> responseEntity = new ResponseEntity<> (
                petToPetDto(petService.create(pet)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH +"/{petId}")
    @Operation(summary = "Животное:получить по id",
            description = "Получить существующий объект Животное")
    public ResponseEntity<PetDto> retrieve(@PathVariable String petId) {
        final Optional<Pet> foundPet = petService.findById(petId);
        if (foundPet.isEmpty()) {
            return new ResponseEntity<PetDto>(HttpStatus.NOT_FOUND);
        } else {
            final PetDto petDto = petToPetDto(foundPet.get());
            return new ResponseEntity<PetDto>(petDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    @Operation(summary = "Животное:получить весь список",
            description = "Получить все существующие в базе объекты Животное")
    public ResponseEntity<List<PetDto>> retrieveAll() {
        final List<Pet> listFound = petService.listAll();
        final List<PetDto> listFoundDtos = listFound.stream()
                .map(pet -> petToPetDto(pet)).toList();
        final ResponseEntity<List<PetDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH +"/{petId}")
    @Operation(summary = "Животное:обновить объект по id",
            description = "Обновить существующий в базе объект Животное")
    public ResponseEntity<PetDto> update(@RequestBody final PetDto petDto) {
        final Pet pet = petDtoToPet(petDto);
        if (!petService.isPresent(pet)) {
            final ResponseEntity<PetDto> responseEntity = new ResponseEntity<> (
                    petToPetDto(petService.save(pet)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<PetDto> responseEntity = new ResponseEntity<> (
                petToPetDto(petService.save(pet)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH +"/{petId}")
    @Operation(summary = "Животное:удалить объект по id",
            description = "Удалить существующий в базе объект Животное")
    public ResponseEntity deleteById(@PathVariable String petId) {
        petService.deleteById(petId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

package com.github.syndexmx.demopetclinic.controller.dtos;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@TemplatedAnnotation
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Животное")
public class PetDto {

    @Schema(description = "Идентификатор")
    private Long id;

    @NotNull(message = "Поле Идентификатор Хозяина должно существовать")
    @NotBlank(message = "Требуется непустое поле Идентификатор Хозяина")
    @Schema(description = "Идентификатор Хозяина", example = "1")
    private Long ownerId;

    @NotNull(message = "Поле Имя должно существовать")
    @NotBlank(message = "Требуется непустое поле Имя")
    @Schema(description = "Имя (кличка)", example = "Китикэт")
    String name;

    @NotNull(message = "Поле Порода должно существовать")
    @NotBlank(message = "Требуется непустое поле Порода")
    @Schema(description = "Порода", example = "Шотландская")
    String breed;

    @NotNull(message = "Поле Дата Рождения должно существовать")
    @NotBlank(message = "Требуется непустое поле Дата Рождения")
    @Schema(description = "Дата Рождения", example = "2020-02-02")
    String birthDate;

    @NotNull(message = "Поле Вес должно существовать")
    @NotBlank(message = "Требуется непустое поле Вес")
    @Schema(description = "Вес в граммах", example = "1000")
    Integer weight; // grams

    @NotNull(message = "Поле Цвет должно существовать")
    @NotBlank(message = "Требуется непустое поле Цвет")
    @Schema(description = "Цвет", example = "Неизвестен")
    String colour;

    @NotNull(message = "Поле Вид должно существовать")
    @NotBlank(message = "Требуется непустое поле Вида")
    @Schema(description = "Вид", example = "CAT")
    private String petSpecies;

    @NotNull(message = "Поле Пол должно существовать")
    @NotBlank(message = "Требуется непустое поле Пол")
    @Schema(description = "Пол", example = "FEMALE")
    private String sex;

    @Schema(description = "Список Визитов (Обращений)", example = "[]")
    private List<Long> admissionIdList;

}

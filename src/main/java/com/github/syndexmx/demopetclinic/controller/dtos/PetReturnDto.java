package com.github.syndexmx.demopetclinic.controller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Животное")
public class PetReturnDto {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Идентификатор Хозяина", example = "1")
    private Long ownerId;

    @Schema(description = "Имя (кличка)", example = "Китикэт")
    String name;

    @Schema(description = "Порода", example = "Шотландская")
    String breed;

    @Schema(description = "Дата Рождения", example = "2020-02-02")
    String birthDate;

    @Schema(description = "Вес в граммах", example = "1000")
    Integer weight; // grams

    @Schema(description = "Цвет", example = "Неизвестен")
    String colour;

    @Schema(description = "Вид", example = "CAT")
    private String petSpecies;

    @Schema(description = "Пол", example = "FEMALE")
    private String sex;

    @Schema(description = "Список Визитов (Обращений)")
    private List<AdmissionDto> admissionList;

}

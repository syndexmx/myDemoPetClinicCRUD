package com.github.syndexmx.demopetclinic.controller.dtos;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@TemplatedAnnotation
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Лечение")
public class TreatmentDto {

    @Schema(description = "Идентификатор")
    private Long id;

    @NotNull(message = "Поле Препарат должно существовать")
    @NotBlank(message = "Требуется непустое поле Препарат")
    @Schema(description = "Препарат", example = "Ибупрофен")
    private String medicine;

    @NotNull(message = "Поле Дозировка должно существовать")
    @NotBlank(message = "Требуется непустое поле Дозировка")
    @Schema(description = "Дозировка", example = "750")
    private Integer dose;

    @NotNull(message = "Поле Количество должно существовать")
    @NotBlank(message = "Требуется непустое поле Количество")
    @Schema(description = "Количество", example = "3")
    private Integer times;

    @NotNull(message = "Поле Дата должно существовать")
    @NotBlank(message = "Требуется непустое поле Дата")
    @Schema(description = "Дата", example = "2025-03-09")
    private String fromDate;

    @NotNull(message = "Поле Дата должно существовать")
    @NotBlank(message = "Требуется непустое поле Дата")
    @Schema(description = "Дата", example = "2025-04-09")
    private String toDate;

    @NotNull(message = "Поле должно существовать")
    @NotBlank(message = "Требуется непустое поле")
    @Schema(description = "Поле", example = "IBUPROPHENE")
    private String treatmentFieldContent;


}

package com.github.syndexmx.demopetclinic.controller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Визит")
public class AdmissionDto {

    @Schema(description = "Идентификатор")
    private Long id;

    @NotNull(message = "Поле Животное должно существовать")
    @NotBlank(message = "Требуется непустое поле Животное")
    @Schema(description = "Животное", example = "1")
    private Long petId;

    @NotNull(message = "Поле Доктор должно существовать")
    @NotBlank(message = "Требуется непустое поле Доктор")
    @Schema(description = "Доктор", example = "1")
    private Long doctorId;

    @NotNull(message = "Поле Дата должно существовать")
    @NotBlank(message = "Требуется непустое поле Дата")
    @Schema(description = "Дата", example = "2025-01-09")
    private String date;

    @NotNull(message = "Поле Проблема должно существовать")
    @NotBlank(message = "Требуется непустое поле Проблема")
    @Schema(description = "Проблема (Причина обращения)", example = "Травма левой передней лапы")
    private String issue;

    @Schema(description = "Осмотр (Результаты)", example = "Наблюдается подвижность осколка большой берцовой кости")
    private String inspection;

    @Schema(description = "Диагноз", example = "Перелом большой берцовой кости")
    private String diagnosis;

    @NotNull(message = "Поле Лечение должно существовать")
    @NotBlank(message = "Требуется непустое поле Лечение")
    @Schema(description = "Лечение", example = "1")
    private Long treatment;

    @NotNull(message = "Поле Тип Визита должно существовать")
    @NotBlank(message = "Требуется непустое поле Тип Визита")
    @Schema(description = "Тип Визита", example = "PROFILAXIS")
    private String admissionKind;


}

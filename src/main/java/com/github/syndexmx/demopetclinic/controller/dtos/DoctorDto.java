package com.github.syndexmx.demopetclinic.controller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Доктор")
public class DoctorDto {

    @Schema(description = "id")
    private Long id;

    @NotNull(message = "Поле Имя должно существовать")
    @NotBlank(message = "Требуется непустое поле Имя")
    @Schema(description = "Имя", example = "Ерофеева Марина")
    private String name;

    @NotNull(message = "Поле Телефон должно существовать")
    @NotBlank(message = "Требуется непустое поле Телефон")
    @Schema(description = "Телефон", example = "+7(999)0090909")
    private String phone;

    @NotNull(message = "Поле Дата Рождения должно существовать")
    @NotBlank(message = "Требуется непустое поле Дата Рождения")
    @Schema(description = "Дата Рождения", example = "1990-12-13")
    private String birthDate;

    @NotNull(message = "Поле Дата Начала Работы должно существовать")
    @NotBlank(message = "Требуется непустое поле Дата Начала Работы")
    @Schema(description = "Дата Начала Работы", example = "2010-07-04")
    private String startDate;

    @NotNull(message = "Поле Специальность должно существовать")
    @NotBlank(message = "Требуется непустое поле Специальность")
    @Schema(description = "Специальность", example = "GP")
    private String doctorSpecialty;


}

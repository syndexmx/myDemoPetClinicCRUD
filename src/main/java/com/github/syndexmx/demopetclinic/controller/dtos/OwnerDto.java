package com.github.syndexmx.demopetclinic.controller.dtos;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TemplatedAnnotation
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Хозяин")
public class OwnerDto {

    @Schema(description = "Идентификатор")
    private Long id;

    @NotNull(message = "Поле Имя должно существовать")
    @NotBlank(message = "Требуется непустое поле Имя")
    @Schema(description = "Имя", example = "Сергей Стрижов")
    private String name;

    @NotNull(message = "Поле Телефон должно существовать")
    @NotBlank(message = "Требуется непустое поле Телефон")
    @Schema(description = "Телефон", example = "+7(999)0090909")
    private String phone;

    @NotNull(message = "Поле Адрес должно существовать")
    @NotBlank(message = "Требуется непустое поле Адрес")
    @Schema(description = "Адрес")
    private Long address;

    @NotNull(message = "Поле должно существовать")
    @NotBlank(message = "Требуется непустое поле")
    @Schema(description = "Поле", example = "DEFAULTVALUE")
    private String ownerFieldContent;


}

package com.github.syndexmx.demopetclinic.controllers.dtos;

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
@Schema(description = "Визит")
public class AdmissionDto {

    @Schema(description = "Идентификатор")
    private Long id;

    @NotNull(message = "Поле должно существовать")
    @NotBlank(message = "Требуется непустое поле")
    @Schema(description = "Поле", example = "DEFAULTVALUE")
    private String admissionFieldContent;


}

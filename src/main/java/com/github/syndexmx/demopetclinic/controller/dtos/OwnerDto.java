package com.github.syndexmx.demopetclinic.controller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private Long addressId;

    @Schema(description = "Список животных", example = "[]")
    private List<Long> petList;

}

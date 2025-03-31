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
@Schema(description = "Хозяин c Животными")
public class OwnerReturnDto {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Имя")
    private String name;

    @Schema(description = "Телефон")
    private String phone;

    @Schema(description = "Адрес")
    private Long addressId;

    @Schema(description = "Список животных")
    private List<PetDto> petList;

}

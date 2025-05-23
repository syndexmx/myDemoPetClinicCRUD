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
@Schema(description = "Адрес")
public class AddressDto {

    @Schema(description = "Идентификатор")
    private Long id;

    @NotNull(message = "Поле Регион должно существовать")
    @NotBlank(message = "Требуется непустое поле Регион")
    @Schema(description = "Регион", example = "Московская область")
    private String region;

    @NotNull(message = "Поле Город должно существовать")
    @NotBlank(message = "Требуется непустое поле Город")
    @Schema(description = "Город (Поселок)", example = "Красногорск")
    private String city;

    @NotNull(message = "Поле Улица должно существовать")
    @NotBlank(message = "Требуется непустое поле Улица")
    @Schema(description = "Улица", example = "Крылова")
    private String street;

    @NotNull(message = "Поле Улица должно существовать")
    @NotBlank(message = "Требуется непустое поле Улица")
    @Schema(description = "Номер дома", example = "5")
    private Integer house;

    @Schema(description = "Здание", example = "3Б")
    private String building;

    @Schema(description = "Квартира", example = "31")
    private Integer flat;

    @Schema(description = "Список жильцов", example = "[]")
    private List<Long> ownerList;


}

package com.github.syndexmx.demopetclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Treatment {

    private Long id;

    private String medicine;
    private MedicineKind medicineKind;
    private Integer dose;
    private Integer times;
    private LocalDate fromDate;
    private LocalDate toDate;

}

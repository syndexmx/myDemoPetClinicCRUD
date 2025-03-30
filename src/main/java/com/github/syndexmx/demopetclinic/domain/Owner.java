package com.github.syndexmx.demopetclinic.domain;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@TemplatedAnnotation
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Owner {

    private Long id;

    private String name;
    private String phone;
    private Long address;

    private List<Long> petIdList;

}

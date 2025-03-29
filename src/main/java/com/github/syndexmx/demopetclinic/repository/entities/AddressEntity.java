package com.github.syndexmx.demopetclinic.repository.entities;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@TemplatedAnnotation
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "addresss")
public class AddressEntity {

    @Id
    private Long addressId;

    private String region;
    private String city;
    private String street;
    private Integer house;
    private String building;
    private Integer flat;

    private String addressFieldContent;


}

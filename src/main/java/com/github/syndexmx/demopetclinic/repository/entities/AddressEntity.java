package com.github.syndexmx.demopetclinic.repository.entities;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@TemplatedAnnotation
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "addresses")
public class AddressEntity {

    @Id
    private Long id;

    private String region;
    private String city;
    private String street;
    private Integer house;
    private String building;
    private Integer flat;

    @OneToMany(cascade = CascadeType.PERSIST) // TODO: Check validity of cascade propagation
    private List<OwnerEntity> ownerList;


}

package com.github.syndexmx.demopetclinic.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "owners")
public class OwnerEntity {

    @Id
    private Long id;

    private String name;
    private String phone;

    private Long addressId;

    @OneToMany(cascade = CascadeType.PERSIST) // TODO: Check validity of cascade propagation
    private List<PetEntity> petList;

}

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
@Table(name = "owners")
public class OwnerEntity {

    @Id
    private Long ownerId;

    private String name;
    private String phone;
    private Long address;

    @OneToMany
    private List<PetEntity> petList;

}

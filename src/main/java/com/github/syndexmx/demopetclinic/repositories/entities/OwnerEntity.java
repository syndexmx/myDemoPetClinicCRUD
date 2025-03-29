package com.github.syndexmx.demopetclinic.repositories.entities;

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
@Table(name = "owners")
public class OwnerEntity {

    @Id
    private Long ownerId;

    private String name;
    private String phone;
    private Long address;

    private String ownerFieldContent;


}

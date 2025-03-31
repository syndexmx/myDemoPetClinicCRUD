package com.github.syndexmx.demopetclinic.repository.reporitories;

import com.github.syndexmx.demopetclinic.repository.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}

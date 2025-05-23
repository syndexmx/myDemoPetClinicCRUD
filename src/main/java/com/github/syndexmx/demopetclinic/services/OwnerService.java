package com.github.syndexmx.demopetclinic.services;

import com.github.syndexmx.demopetclinic.domain.Owner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OwnerService {

    Owner create(Owner owner);

    Owner save(Owner owner);

    Optional<Owner> findById(Long ownerId);

    List<Owner> listAll();

    boolean isPresent(Long ownerId);

    boolean isPresent(Owner owner);

    void deleteById(Long ownerId);

    public void casscadeAssignAddress(Long ownerId, Long addressId);
}

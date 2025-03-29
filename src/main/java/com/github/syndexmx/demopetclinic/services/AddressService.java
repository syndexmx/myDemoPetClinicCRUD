package com.github.syndexmx.demopetclinic.services;

import com.github.syndexmx.demopetclinic.annotations.TemplatedAnnotation;
import com.github.syndexmx.demopetclinic.domain.Address;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@TemplatedAnnotation
@Service
public interface AddressService {

    Address create(Address address);

    Address save(Address address);

    Optional<Address> findById(String addressId);

    List<Address> listAll();

    boolean isPresent(String addressId);

    boolean isPresent(Address address);

    void deleteById(String addressId);

}

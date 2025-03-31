package com.github.syndexmx.demopetclinic.services;

import com.github.syndexmx.demopetclinic.domain.Address;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AddressService {

    Address create(Address address);

    Address save(Address address);

    Optional<Address> findById(Long addressId);

    List<Address> listAll();

    boolean isPresent(Long addressId);

    boolean isPresent(Address address);

    void deleteById(Long addressId);

}

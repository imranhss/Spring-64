package com.emranhss.project.service;

import com.emranhss.project.dto.AddressResponseDTO;
import com.emranhss.project.entity.Address;
import com.emranhss.project.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public List<AddressResponseDTO> getAllAddressesDTO() {
        return addressRepository.findAll().stream().map(addr -> {
            AddressResponseDTO dto = new AddressResponseDTO();
            dto.setId(addr.getId());
            dto.setAddressLine1(addr.getAddressLine1());
            dto.setAddressLine2(addr.getAddressLine2());
            dto.setCountryName(addr.getCountry() != null ? addr.getCountry().getName() : null);
            dto.setDivisionName(addr.getDivision() != null ? addr.getDivision().getName() : null);
            dto.setDistrictName(addr.getDistrict() != null ? addr.getDistrict().getName() : null);
            dto.setPoliceStationName(addr.getPoliceStation() != null ? addr.getPoliceStation().getName() : null);
            return dto;
        }).toList();
    }


    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with ID: " + id));
    }

    public void deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new RuntimeException("Address not found with ID: " + id);
        }
        addressRepository.deleteById(id);
    }

}

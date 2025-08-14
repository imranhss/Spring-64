package com.emranhss.project.service;

import com.emranhss.project.dto.CountryResponseDTO;
import com.emranhss.project.dto.DivisionResponseDTO;
import com.emranhss.project.entity.Country;
import com.emranhss.project.entity.Division;
import com.emranhss.project.repository.ICountryRepo;
import com.emranhss.project.repository.IDivisionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DivisionService {


    @Autowired
    private IDivisionRepo divisionRepo;

    public List<Division> getAllDivisions() {
        return divisionRepo.findAll();
    }

    public List<DivisionResponseDTO> getAllDivisionDTOs() {
        return divisionRepo.findAll().stream().map(d -> {
            DivisionResponseDTO dto = new DivisionResponseDTO();
            dto.setId(d.getId());
            dto.setName(d.getName());

            Country c = d.getCountry();
            if (c != null) {
                CountryResponseDTO countryDTO = new CountryResponseDTO();
                countryDTO.setId(c.getId());
                countryDTO.setName(c.getName());
                dto.setCountry(countryDTO);
            }

            return dto;
        }).toList();
    }

    public Optional<Division> findById(int id) {
        return divisionRepo.findById(id);
    }

    public Division saveDivision(Division division) {
        return divisionRepo.save(division);
    }

    public void deleteById(int id) {
        divisionRepo.deleteById(id);
    }


//    public List<Division> getByCountryId(int countryId) {
//        return divisionRepo.findByCountryId(countryId);
//    }

    // DivisionService
    public List<DivisionResponseDTO> getByCountryId(int countryId) {
        return divisionRepo.findByCountryId(countryId)
                .stream()
                .map(d -> new DivisionResponseDTO(d.getId(), d.getName(), null))
                .toList();
    }


}

package com.emranhss.project.service;

import com.emranhss.project.dto.DivisionResponseDTO;
import com.emranhss.project.entity.Country;
import com.emranhss.project.entity.Division;
import com.emranhss.project.repository.ICountryRepo;
import com.emranhss.project.repository.IDivisionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionService {

    @Autowired
    private IDivisionRepo divisionRepository;
    @Autowired
    private ICountryRepo countryRepo;

    public List<Division> getAllDivisions() {
        return divisionRepository.findAll();
    }

    public List<DivisionResponseDTO> getAllDivisionDTOs() {
        return getAllDivisions().stream().map(div -> {
            DivisionResponseDTO dto = new DivisionResponseDTO();
            dto.setId(div.getId());
            dto.setName(div.getName());

            List<Integer> districtIds = div.getDistricts().stream()
                    .map(d -> d.getId())
                    .toList();
            dto.setDistricts(districtIds);

            return dto;
        }).toList();
    }

    public Division saveDivision(Division division) {
        if(division.getCountry()    != null) {
            long countryId = division.getCountry().getId();
            Country country = countryRepo.findById(countryId)
                    .orElseThrow(() -> new RuntimeException("Country not found WITH ID: " + countryId));

            division.setCountry(country);
        }

        return divisionRepository.save(division);
    }


}

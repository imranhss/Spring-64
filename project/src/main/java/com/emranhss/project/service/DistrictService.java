package com.emranhss.project.service;

import com.emranhss.project.dto.DistrictResponseDTO;
import com.emranhss.project.dto.DivisionResponseDTO;
import com.emranhss.project.entity.District;
import com.emranhss.project.entity.Division;
import com.emranhss.project.repository.IDistrictRepo;
import com.emranhss.project.repository.IDivisionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DistrictService {

    @Autowired
    private IDistrictRepo districtRepo;
    @Autowired
    private IDivisionRepo divisionRepo;


    public List<DistrictResponseDTO> getAllDistricts() {
        return districtRepo.findAll().stream().map(district -> {
            DistrictResponseDTO dto = new DistrictResponseDTO();
            dto.setId(district.getId());
            dto.setName(district.getName());

            Division division = district.getDivision();
            if (division != null) {
                DivisionResponseDTO divisionDTO = new DivisionResponseDTO();
                divisionDTO.setId(division.getId());
                divisionDTO.setName(division.getName());
                dto.setDivision(divisionDTO);
            }

            return dto;
        }).toList();
    }


    public District saveOrUpdateDistrict(District district) {
        return districtRepo.save(district);
    }

    public Optional<District> getDistrictById(int id) {
        return districtRepo.findById(id);
    }

    public void deleteDistrictById(int id) {
        districtRepo.deleteById(id);
    }

    // To create District linked to a Division by divisionId
    public District createDistrict(District district, int divisionId) {
        Division division = divisionRepo.findById(divisionId)
                .orElseThrow(() -> new RuntimeException("Division not found with id " + divisionId));
        district.setDivision(division);
        return districtRepo.save(district);
    }


//    public List<District> getByDivisionId(int divisionId) {
//        return districtRepo.findByDivisionId(divisionId);
//    }

    // DistrictService
    public List<DistrictResponseDTO> getByDivisionId(int divisionId) {
        return districtRepo.findByDivisionId(divisionId)
                .stream()
                .map(d -> new DistrictResponseDTO(d.getId(), d.getName(), null))
                .toList();
    }


}



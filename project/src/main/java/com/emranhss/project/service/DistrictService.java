package com.emranhss.project.service;

import com.emranhss.project.dto.DistrictResponseDTO;
import com.emranhss.project.entity.District;
import com.emranhss.project.entity.Division;
import com.emranhss.project.repository.IDistrictRepo;
import com.emranhss.project.repository.IDivisionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {

    @Autowired
    private IDistrictRepo districtRepo;
    @Autowired
    private IDivisionRepo divisionRepo;

    public void save(District district) {

        if(district.getDivision() != null){

            int divId = district.getDivision().getId();
            Division division = divisionRepo.findById(divId)
                    .orElseThrow(() -> new RuntimeException("Division not found WITH THIS ID: " + divId));

            district.setDivision(division);
        }

        districtRepo.save(district);
    }


    public List<District> getAllDistrict() {
        return districtRepo.findAll();
    }


    public List<DistrictResponseDTO> getAllDistrictDTOs() {
        List<District> districts = getAllDistrict();

        return districts.stream().map(d -> {
            DistrictResponseDTO dto = new DistrictResponseDTO();
            dto.setId(d.getId());
            dto.setName(d.getName());

            // Map PoliceStations to their IDs only
            List<Integer> psIds = d.getPoliceStations().stream()
                    .map(ps -> ps.getId())
                    .toList();

            dto.setPoliceStations(psIds);
            return dto;
        }).toList();
    }

    public District getDistrictById(int id) {
        return districtRepo.findById(id).get();
    }

    public void deleteDistrictById(int id) {
        districtRepo.deleteById(id);
    }

    public District getDistrictByName(String name) {
        return districtRepo.findByName(name);
    }

}

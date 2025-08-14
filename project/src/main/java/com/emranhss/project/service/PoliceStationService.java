package com.emranhss.project.service;

import com.emranhss.project.dto.DistrictResponseDTO;
import com.emranhss.project.dto.PoliceStationResponseDTO;
import com.emranhss.project.entity.District;
import com.emranhss.project.entity.PoliceStation;
import com.emranhss.project.repository.IPoliceStationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliceStationService {

    @Autowired
    private IPoliceStationRepo policeStationRepo;


    public PoliceStation saveOrUpdate(PoliceStation ps) {
        return policeStationRepo.save(ps);
    }




    public List<PoliceStationResponseDTO> getAllPoliceStationsDTOs() {
        return policeStationRepo.findAll().stream().map(ps -> {
            PoliceStationResponseDTO dto = new PoliceStationResponseDTO();
            dto.setId(ps.getId());
            dto.setName(ps.getName());

            District district = ps.getDistrict();
            if (district != null) {
                DistrictResponseDTO districtDTO = new DistrictResponseDTO();
                districtDTO.setId(district.getId());
                districtDTO.setName(district.getName());
                dto.setDistrict(districtDTO);
            }

            return dto;
        }).toList();
    }


    public Optional<PoliceStation> findById(Integer id) {
        return policeStationRepo.findById(id);
    }

    public void deleteById(Integer id) {
        policeStationRepo.deleteById(id);
    }


    public PoliceStation update(Integer id, PoliceStation updatedPoliceStation) {
        return policeStationRepo.findById(id).map(existingPs -> {
            existingPs.setName(updatedPoliceStation.getName());

            // Update district if provided
            if (updatedPoliceStation.getDistrict() != null) {
                existingPs.setDistrict(updatedPoliceStation.getDistrict());
            }

            return policeStationRepo.save(existingPs);
        }).orElseThrow(() -> new RuntimeException("PoliceStation not found with id " + id));
    }

//    public List<PoliceStation> getByDistrictId(int districtId) {
//        return policeStationRepo.findByDistrictId(districtId);
//    }

    // PoliceStationService
    public List<PoliceStationResponseDTO> getByDistrictId(int districtId) {
        return policeStationRepo.findByDistrictId(districtId)
                .stream()
                .map(ps -> new PoliceStationResponseDTO(ps.getId(), ps.getName(), null))
                .toList();
    }


}

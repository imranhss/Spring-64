package com.emranhss.project.service;

import com.emranhss.project.dto.PoliceStationResponseDTO;
import com.emranhss.project.entity.District;
import com.emranhss.project.entity.PoliceStation;
import com.emranhss.project.repository.IDistrictRepo;
import com.emranhss.project.repository.IPoliceStationRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PoliceStationService {

    @Autowired
    private IPoliceStationRepo policeStationRepo;

    @Autowired
    private IDistrictRepo districtRepo;

    public List<PoliceStationResponseDTO> getAllPoliceStationDTOs() {
        return policeStationRepo.findAll().stream().map(ps -> {
            PoliceStationResponseDTO dto = new PoliceStationResponseDTO();
            dto.setId(ps.getId());
            dto.setName(ps.getName());

            if (ps.getDistrict() != null) {
                dto.setDistrictId(ps.getDistrict().getId());
                dto.setDistrictName(ps.getDistrict().getName());
            }
            return dto;
        }).toList();
    }

    @Transactional
    public PoliceStation create(PoliceStation policeStation) {
        if (policeStation.getDistrict() != null) {
            int districtId = policeStation.getDistrict().getId();
            District district = districtRepo.findById(districtId)
                    .orElseThrow(() -> new RuntimeException("District not found with id " + districtId));
            policeStation.setDistrict(district);
        }
        return policeStationRepo.save(policeStation);
    }

    // Read all
    public List<PoliceStation> findAll() {
        return policeStationRepo.findAll();
    }

    // Read one by ID
    public Optional<PoliceStation> findById(int id) {
        return policeStationRepo.findById(id);
    }

    // Update by ID
    public PoliceStation update(int id, PoliceStation updatedPoliceStation) {
        PoliceStation existing = policeStationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("PoliceStation not found with id " + id));

        existing.setName(updatedPoliceStation.getName());

        if (updatedPoliceStation.getDistrict() != null) {
            // Optionally verify district exists
            District district = districtRepo.findById(updatedPoliceStation.getDistrict().getId())
                    .orElseThrow(() -> new RuntimeException("District not found with id " + updatedPoliceStation.getDistrict().getId()));
            existing.setDistrict(district);
        }

        return policeStationRepo.save(existing);
    }

    // Delete by ID
    public void delete(int id) {
        policeStationRepo.deleteById(id);
    }
}

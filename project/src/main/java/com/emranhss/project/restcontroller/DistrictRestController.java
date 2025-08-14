package com.emranhss.project.restcontroller;


import com.emranhss.project.dto.DistrictResponseDTO;
import com.emranhss.project.entity.District;
import com.emranhss.project.service.DistrictService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/district/")
public class DistrictRestController {

    @Autowired
    private DistrictService districtService;

    @GetMapping("")
    public List<DistrictResponseDTO> getAllDistricts() {
        return districtService.getAllDistricts();
    }

    @GetMapping("{id}")
    public District getDistrictById(@PathVariable int id) {
        return districtService.getDistrictById(id).orElseThrow(() -> new RuntimeException("District not found"));
    }

    @PostMapping("")
    public District saveDistrict(@RequestBody District district, @RequestParam int divisionId) {
        return districtService.createDistrict(district, divisionId);
    }

    @PutMapping("{id}")
    public District updateDistrict(@PathVariable int id, @RequestBody District district) {
        district.setId(id);
        return districtService.saveOrUpdateDistrict(district);
    }

    @DeleteMapping("{id}")
    public void deleteDistrict(@PathVariable int id) {
        districtService.deleteDistrictById(id);
    }


    // DistrictRestController
    @GetMapping("/by-division/{divisionId}")
    public List<DistrictResponseDTO> getByDivision(@PathVariable int divisionId) {
        return districtService.getByDivisionId(divisionId);
    }




}

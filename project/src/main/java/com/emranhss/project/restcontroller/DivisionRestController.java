package com.emranhss.project.restcontroller;

import com.emranhss.project.dto.DivisionResponseDTO;
import com.emranhss.project.entity.Division;
import com.emranhss.project.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/division/")
public class DivisionRestController {

    @Autowired
    private DivisionService divisionService;



    @GetMapping("")
    public List<DivisionResponseDTO> getAllDivisions() {
        return divisionService.getAllDivisionDTOs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Division> getDivisionById(@PathVariable int id) {
        Optional<Division> division = divisionService.findById(id);
        return division.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Division> createDivision(@RequestBody Division division) {
        Division saved = divisionService.saveDivision(division);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Division> updateDivision(@PathVariable int id, @RequestBody Division division) {
        Optional<Division> existing = divisionService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Division toUpdate = existing.get();
        toUpdate.setName(division.getName());
        toUpdate.setCountry(division.getCountry());
        // Usually, districts are managed separately

        Division updated = divisionService.saveDivision(toUpdate);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDivision(@PathVariable int id) {
        Optional<Division> existing = divisionService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        divisionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    // DivisionRestController
    @GetMapping("/by-country/{countryId}")
    public List<DivisionResponseDTO> getByCountry(@PathVariable int countryId) {
        return divisionService.getByCountryId(countryId);
    }



}

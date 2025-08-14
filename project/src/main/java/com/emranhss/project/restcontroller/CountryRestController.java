package com.emranhss.project.restcontroller;

import com.emranhss.project.dto.CountryResponseDTO;
import com.emranhss.project.entity.Country;
import com.emranhss.project.repository.ICountryRepo;
import com.emranhss.project.service.CountryService;
import com.emranhss.project.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries/")
public class CountryRestController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private ICountryRepo countryRepo;



    // Get all countries as DTOs
    @GetMapping("")
    public List<CountryResponseDTO> getAllCountries() {
        return countryService.getAllCountryDTOs();
    }

    // Get single country by ID (full entity)
//    @GetMapping("/{id}")
//    public ResponseEntity<Country> getCountryById(@PathVariable int id) {
//        Optional<Country> country = countryService.getAllCountries()
//                .stream()
//                .filter(c -> c.getId() == id)
//                .findFirst();
//
//        return country.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable int id) {
        return countryRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Create new country
    @PostMapping("")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        Country savedCountry = countryService.saveCountry(country);
        return ResponseEntity.ok(savedCountry);
    }

    // Update existing country
    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable int id, @RequestBody Country country) {
        Optional<Country> existingCountryOpt = countryService.getAllCountries()
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst();

        if (existingCountryOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Country existingCountry = existingCountryOpt.get();
        existingCountry.setName(country.getName());
        // You can update divisions if needed, but usually divisions are managed separately

        Country updatedCountry = countryService.saveCountry(existingCountry);
        return ResponseEntity.ok(updatedCountry);
    }

    // Delete country by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable int id) {
        Optional<Country> existingCountryOpt = countryService.getAllCountries()
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst();

        if (existingCountryOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        countryService.saveCountry(existingCountryOpt.get()); // you can remove this line if unnecessary
        countryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

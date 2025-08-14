package com.emranhss.project.dto;

public class AddressResponseDTO {

    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String countryName;
    private String divisionName;
    private String districtName;
    private String policeStationName;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAddressLine1() { return addressLine1; }
    public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }
    public String getAddressLine2() { return addressLine2; }
    public void setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2; }
    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) { this.countryName = countryName; }
    public String getDivisionName() { return divisionName; }
    public void setDivisionName(String divisionName) { this.divisionName = divisionName; }
    public String getDistrictName() { return districtName; }
    public void setDistrictName(String districtName) { this.districtName = districtName; }
    public String getPoliceStationName() { return policeStationName; }
    public void setPoliceStationName(String policeStationName) { this.policeStationName = policeStationName; }
}

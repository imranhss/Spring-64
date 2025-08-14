import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AddressService {

    private api = 'http://localhost:8085/api';  // Spring Boot URL

    constructor(private http: HttpClient) { }

    getCountries(): Observable<any[]> {
        return this.http.get<any[]>(`${this.api}/countries/`);
    }

    getDivisionsByCountry(countryId: number): Observable<any[]> {
        return this.http.get<any[]>(`${this.api}/division/by-country/${countryId}`);
    }

    getDistrictsByDivision(divisionId: number): Observable<any[]> {
        return this.http.get<any[]>(`${this.api}/district/by-division/${divisionId}`);
    }

    getPoliceStationsByDistrict(districtId: number): Observable<any[]> {
        return this.http.get<any[]>(`${this.api}/policestation/by-district/${districtId}`);
    }

    // Save address
    saveAddress(address: any): Observable<any> {
        return this.http.post(`${this.api}/addresses`, address);
    }

    getAllAddresses(): Observable<any[]> {
        return this.http.get<any[]>(`${this.api}/addresses`);
    }


}

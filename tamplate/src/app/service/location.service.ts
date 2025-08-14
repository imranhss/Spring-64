import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  
  private api = 'http://localhost:8085/api'; // adjust base URL if needed

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


}

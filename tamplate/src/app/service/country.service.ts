import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Country } from '../model/country.model';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class CountryService {

   private baseUrl = environment.apiBaseUrl + '/countries/';


  constructor(private http: HttpClient) {}

  getAll(): Observable<Country[]> {
    return this.http.get<Country[]>(this.baseUrl);
  }

  getById(id: number): Observable<Country> {
    return this.http.get<Country>(`${this.baseUrl}${id}`);
  }

  create(country: Country): Observable<Country> {
    return this.http.post<Country>(this.baseUrl, country);
  }

  update(id: number, country: Country): Observable<Country> {
    return this.http.put<Country>(`${this.baseUrl}${id}`, country);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}${id}`);
  }
}

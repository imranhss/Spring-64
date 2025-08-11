import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Division } from '../model/division.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DivisionService {


     private baseUrl = environment.apiBaseUrl + '/division/';


  constructor(private http: HttpClient) { }

  getAll(): Observable<Division[]> {
    return this.http.get<Division[]>(this.baseUrl);
  }

  getById(id: number): Observable<Division> {
    return this.http.get<Division>(`${this.baseUrl}${id}`);
  }

  create(division: Division): Observable<Division> {
    return this.http.post<Division>(this.baseUrl, division);
  }

  update(id: number, division: Division): Observable<Division> {
    return this.http.put<Division>(`${this.baseUrl}${id}`, division);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}${id}`);
  }


}

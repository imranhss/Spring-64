import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { District } from '../model/district.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DistrictService {

  private baseUrl = environment.apiBaseUrl + '/district/';



  constructor(private http: HttpClient) { }

  getAll(): Observable<District[]> {
    return this.http.get<District[]>(this.baseUrl);
  }

  getById(id: number): Observable<District> {
    return this.http.get<District>(`${this.baseUrl}${id}`);
  }

  create(district: District, divisionId: number): Observable<District> {
    const params = new HttpParams().set('divisionId', divisionId.toString());
    return this.http.post<District>(this.baseUrl, district, { params });
  }

  update(id: number, district: District): Observable<District> {
    return this.http.put<District>(`${this.baseUrl}${id}`, district);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}${id}`);
  }
}

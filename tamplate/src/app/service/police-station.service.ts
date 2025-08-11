import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PoliceStation } from '../model/police-station.model';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class PoliceStationService {


  private baseUrl = environment.apiBaseUrl + '/policestation/';



  constructor(private http: HttpClient) {}

  getAll(): Observable<PoliceStation[]> {
    return this.http.get<PoliceStation[]>(this.baseUrl);
  }

  create(ps: PoliceStation): Observable<PoliceStation> {
    return this.http.post<PoliceStation>(this.baseUrl, ps);
  }

  update(id: number, ps: PoliceStation): Observable<PoliceStation> {
    return this.http.put<PoliceStation>(`${this.baseUrl}${id}`, ps);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}${id}`);
  }
}

import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Observable } from 'rxjs';
import { Extracurricular } from '../model/extracurricular';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class ExtracurricularService {

    private baseUrl = environment.apiBaseUrl + '/extracurricular/';



  constructor(private http: HttpClient,
     @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  // Add new extracurricular
  addExtracurricular(data: Extracurricular): Observable<Extracurricular> {

    let headers = new HttpHeaders();

    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken');
      if (token) {
        headers = headers.set('Authorization', 'Bearer ' + token);
      }
    }

    return this.http.post<Extracurricular>(`${this.baseUrl}add`, data, { headers });
  }

  // Get all extracurriculars for logged-in user
  getAllExtracurriculars(): Observable<Extracurricular[]> {
 let headers = new HttpHeaders();

    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken');
      if (token) {
        headers = headers.set('Authorization', 'Bearer ' + token);
      }
    }

    return this.http.get<Extracurricular[]>(`${this.baseUrl}all`,  { headers });
  }

  // Delete an extracurricular by ID
  deleteExtracurricular(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}${id}`);
  }
}

import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { isPlatformBrowser } from '@angular/common';
import { Observable } from 'rxjs';
import { Reference } from '../model/reference';

@Injectable({
  providedIn: 'root'
})
export class ReferenceService {

  private baseUrl = environment.apiBaseUrl + '/reference/';

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  addReference(data: Reference): Observable<Reference> {
    let headers = new HttpHeaders();

    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken');
      if (token) {
        headers = headers.set('Authorization', 'Bearer ' + token);
      }
    }

    return this.http.post<Reference>(`${this.baseUrl}add`, data, { headers });
  }

  getAllReferences(): Observable<Reference[]> {
    let headers = new HttpHeaders();

    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken');
      if (token) {
        headers = headers.set('Authorization', 'Bearer ' + token);
      }
    }

    return this.http.get<Reference[]>(`${this.baseUrl}all`, { headers });
  }

  deleteReference(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}${id}`);
  }
}

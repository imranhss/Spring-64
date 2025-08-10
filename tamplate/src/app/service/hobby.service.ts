import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { isPlatformBrowser } from '@angular/common';
import { Observable } from 'rxjs';
import { Hobby } from '../model/hobby';

@Injectable({
  providedIn: 'root'
})
export class HobbyService {

  private baseUrl = environment.apiBaseUrl + '/hobby/';

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  addHobby(data: Hobby): Observable<Hobby> {
    let headers = new HttpHeaders();

    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken');
      if (token) {
        headers = headers.set('Authorization', 'Bearer ' + token);
      }
    }

    return this.http.post<Hobby>(`${this.baseUrl}add`, data, { headers });
  }

  getAllHobbies(): Observable<Hobby[]> {
    let headers = new HttpHeaders();

    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken');
      if (token) {
        headers = headers.set('Authorization', 'Bearer ' + token);
      }
    }

    return this.http.get<Hobby[]>(`${this.baseUrl}all`, { headers });
  }

  deleteHobby(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}${id}`);
  }
}

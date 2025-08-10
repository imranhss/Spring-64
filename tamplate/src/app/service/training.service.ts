import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { isPlatformBrowser } from '@angular/common';
import { Observable } from 'rxjs';
import { Training } from '../model/training';

@Injectable({
  providedIn: 'root'
})
export class TrainingService {

  private baseUrl = environment.apiBaseUrl + '/training/';

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  addTraining(data: Training): Observable<Training> {
    let headers = new HttpHeaders();

    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken');
      if (token) {
        headers = headers.set('Authorization', 'Bearer ' + token);
      }
    }

    return this.http.post<Training>(`${this.baseUrl}add`, data, { headers });
  }

  getAllTrainings(): Observable<Training[]> {
    let headers = new HttpHeaders();

    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken');
      if (token) {
        headers = headers.set('Authorization', 'Bearer ' + token);
      }
    }

    return this.http.get<Training[]>(`${this.baseUrl}all`, { headers });
  }

  deleteTraining(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}${id}`);
  }
}

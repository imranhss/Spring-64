import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { isPlatformBrowser } from '@angular/common';
import { Observable } from 'rxjs';
import { Language } from '../model/language';

@Injectable({
  providedIn: 'root'
})
export class LanguageService {

  private baseUrl = environment.apiBaseUrl + '/language/';

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  addLanguage(data: Language): Observable<Language> {
    let headers = new HttpHeaders();

    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken');
      if (token) {
        headers = headers.set('Authorization', 'Bearer ' + token);
      }
    }

    return this.http.post<Language>(`${this.baseUrl}add`, data, { headers });
  }

  getAllLanguages(): Observable<Language[]> {
    let headers = new HttpHeaders();

    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken');
      if (token) {
        headers = headers.set('Authorization', 'Bearer ' + token);
      }
    }

    return this.http.get<Language[]>(`${this.baseUrl}all`, { headers });
  }

  deleteLanguage(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}${id}`);
  }
}

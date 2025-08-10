import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Observable } from 'rxjs';
import { Skill } from '../model/skill';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class SkillService {

  private baseUrl = environment.apiBaseUrl + '/skill/';

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  // Add new skill
  addSkill(data: Skill): Observable<Skill> {
    let headers = new HttpHeaders();

    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken');
      if (token) {
        headers = headers.set('Authorization', 'Bearer ' + token);
      }
    }

    return this.http.post<Skill>(`${this.baseUrl}add`, data, { headers });
  }

  // Get all skills for logged-in user
  getAllSkills(): Observable<Skill[]> {
    let headers = new HttpHeaders();

    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken');
      if (token) {
        headers = headers.set('Authorization', 'Bearer ' + token);
      }
    }

    return this.http.get<Skill[]>(`${this.baseUrl}all`, { headers });
  }

  // Delete a skill by ID
  deleteSkill(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}${id}`);
  }
}

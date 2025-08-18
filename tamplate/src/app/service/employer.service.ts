import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { AuthService } from './auth-service';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class EmployerService {

  private baseUrl = environment.apiBaseUrl + '/employer/';



  constructor(private http: HttpClient, private authService: AuthService,
    @Inject(PLATFORM_ID) private platformId: Object) { }

  // 1️⃣ Register Employer with User + Employer JSON + Photo
  registerEmployer(user: any, employer: any, photo: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('user', JSON.stringify(user));
    formData.append('employer', JSON.stringify(employer));
    formData.append('photo', photo);

    return this.http.post(this.baseUrl, formData);
  }

  // 2️⃣ Get All Employers
  getAllEmployers(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl + 'all');
  }

  getProfile(): Observable<any> {
    let headers = new HttpHeaders();

    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken');
      if (token) {
        headers = headers.set('Authorization', 'Bearer ' + token);
        console.log(headers);
      }
    }
    return this.http.get<any>(this.baseUrl + 'profile', { headers });
  }


}

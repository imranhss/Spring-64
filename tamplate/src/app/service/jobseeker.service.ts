import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JobSeeker } from '../model/jobseeker.model';
import { AuthService } from './auth-service';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class JobseekerService {

  private baseUrl = environment.apiBaseUrl + '/jobseeker/';

  constructor(private http: HttpClient, private authService: AuthService,
    @Inject(PLATFORM_ID) private platformId: Object
  ) { }




  registerJobSeeker(user: any, jobSeeker: any, photo: File): Observable<any> {
    const formData = new FormData();
    formData.append('user', JSON.stringify(user));
    formData.append('jobSeeker', JSON.stringify(jobSeeker));
    formData.append('photo', photo);

    return this.http.post(this.baseUrl, formData);
  }

  getProfile(): Observable<JobSeeker> {
    let headers = new HttpHeaders();

    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken');
      if (token) {
        headers = headers.set('Authorization', 'Bearer ' + token);
        console.log(headers);
      }
    }

    return this.http.get<JobSeeker>(`${environment.apiBaseUrl}/jobseeker/profile`, { headers });
  }

}

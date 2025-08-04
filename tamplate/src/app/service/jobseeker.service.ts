import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JobSeeker } from '../model/jobseeker.model';
import { AuthService } from './auth-service';

@Injectable({
  providedIn: 'root'
})
export class JobseekerService {

  private baseUrl = environment.apiBaseUrl + '/jobseeker/';

  constructor(private http: HttpClient, private authService: AuthService) { }




  registerJobSeeker(user: any, jobSeeker: any, photo: File): Observable<any> {
    const formData = new FormData();
    formData.append('user', JSON.stringify(user));
    formData.append('jobSeeker', JSON.stringify(jobSeeker));
    formData.append('photo', photo);

    return this.http.post(this.baseUrl, formData);
  }

  getProfile(): Observable<JobSeeker> {
    const token = localStorage.getItem('authToken');
    let headers = new HttpHeaders();

    if (token) {
      headers = headers.set('Authorization', 'Bearer ' + token);
    }

    return this.http.get<JobSeeker>(`${this.baseUrl}profile`, { headers });
  }

}

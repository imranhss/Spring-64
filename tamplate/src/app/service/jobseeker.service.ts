import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JobseekerService {

   private baseUrl = environment.apiBaseUrl+'/jobseeker/';

  constructor(private http:HttpClient) { }



  registerJobSeeker(user: any, jobSeeker: any, photo: File): Observable<any> {
    const formData = new FormData();
    formData.append('user', JSON.stringify(user));
    formData.append('jobSeeker', JSON.stringify(jobSeeker));
    formData.append('photo', photo);

    return this.http.post(this.baseUrl, formData);
  }


}

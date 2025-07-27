import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StudentModule } from '../model/student/student-module';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  baseUrl: string = "http://localhost:8085/student/";

  constructor(
    private http: HttpClient,
  ) { }


  saveStudent(student: StudentModule): Observable<StudentModule> {

    return this.http.post<StudentModule>(this.baseUrl, student);

  }

}

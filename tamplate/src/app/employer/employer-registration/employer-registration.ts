import { Component } from '@angular/core';
import { EmployerService } from '../../service/employer.service';

@Component({
  selector: 'app-employer-registration',
  standalone: false,
  templateUrl: './employer-registration.html',
  styleUrl: './employer-registration.css'
})
export class EmployerRegistration {


  // ✅ User fields
  user: any = {
    name: '',
    email: '',
    phone: '',
    password: '',
    role: 'EMPLOYER'
  };

  // ✅ Employer fields
  employer: any = {
    companyName: '',
    companyWebsite: '',
    companyAddress: '',
    contactPerson: '',
    phone: '',
    email: ''
  };

  // ✅ Logo file
  photoFile: File | null = null;

  constructor(private employerService: EmployerService) {}

  onFileSelected(event: any) {
    this.photoFile = event.target.files[0];
  }

  registerEmployer() {
    if (!this.photoFile) {
      alert("Please select a logo/photo before submitting");
      return;
    }

    this.employerService.registerEmployer(this.user, this.employer, this.photoFile)
      .subscribe({
        next: (res) => {
          alert("Employer registered successfully ✅");
          console.log(res);
        },
        error: (err) => {
          alert("Registration failed ❌");
          console.error(err);
        }
      });
  }


}

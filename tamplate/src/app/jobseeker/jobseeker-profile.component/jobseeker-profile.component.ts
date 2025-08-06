import { ChangeDetectorRef, Component } from '@angular/core';
import { JobseekerService } from '../../service/jobseeker.service';
import { JobSeeker } from '../../model/jobseeker.model';
import { EducationService } from '../../service/education.service';
import { Education } from '../../model/education';

@Component({
  selector: 'app-jobseeker-profile.component',
  standalone: false,
  templateUrl: './jobseeker-profile.component.html',
  styleUrl: './jobseeker-profile.component.css'
})
export class JobseekerProfileComponent {

  jobSeeker: any;

    educations: Education[] = [];

  newEducation = {
    level: '',
    institute: '',
    result: '',
    year: ''
  };

  constructor(private jobSeekerService: JobseekerService, private cdr: ChangeDetectorRef,
    private educationService: EducationService
  ) { }

  ngOnInit(): void {
    this.getProfile();
    this.loadEducations();

  }


  loadEducations(): void {
    this.educationService.getEducations().subscribe({
      next: (data) => {
        this.educations = data;
        
        this.cdr.markForCheck();
       
      },
      error: (err) => {
        console.error('Failed to load educations', err);
      }
    });
  }

  getProfile() {

    this.jobSeekerService.getProfile().subscribe({
      next: (data) => {
        this.jobSeeker = data;
        console.log(data);
        this.cdr.markForCheck();

      },
      error: (err) => {
        console.error('Failed to load profile', err);
      }
    });
  }


  addEducation(): void {
    this.educationService.addEducation(this.newEducation).subscribe({
      next: (addedEdu: any) => {
        if (!this.jobSeeker.educations) {
          this.jobSeeker.educations = [];
        }
        this.jobSeeker.educations.push(addedEdu);  // Update UI
        this.newEducation = { level: '', institute: '', result: '', year: '' };  // Reset form
      },
      error: (err) => {
        console.error('Failed to add education', err);
      }
    });
  }

}

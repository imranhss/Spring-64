import { ChangeDetectorRef, Component } from '@angular/core';
import { JobseekerService } from '../../service/jobseeker.service';
import { JobSeeker } from '../../model/jobseeker.model';

@Component({
  selector: 'app-jobseeker-profile.component',
  standalone: false,
  templateUrl: './jobseeker-profile.component.html',
  styleUrl: './jobseeker-profile.component.css'
})
export class JobseekerProfileComponent {

  jobSeeker?: JobSeeker;

  constructor(private jobSeekerService: JobseekerService, private cdr:ChangeDetectorRef) {}

  ngOnInit(): void {
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

}

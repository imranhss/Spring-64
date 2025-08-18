import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { EmployerService } from '../../service/employer.service';

@Component({
  selector: 'app-employer-profile.component',
  standalone: false,
  templateUrl: './employer-profile.component.html',
  styleUrl: './employer-profile.component.css'
})
export class EmployerProfileComponent implements OnInit{


  profile: any = null;
  loading = true;
  error = '';

  constructor(private employerService: EmployerService,
    private cd: ChangeDetectorRef 
  ) { }

  ngOnInit(): void {
    this.getProfile();
  }

  getProfile() {
    this.employerService.getProfile().subscribe({
      next: (data) => {
        this.profile = data;
        this.cd.markForCheck();
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load profile ❌';
        console.error(err);
        this.loading = false;
      }
    });
  }


}

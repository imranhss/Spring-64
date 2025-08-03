import { Component } from '@angular/core';
import { User } from '../../model/user.model';
import { Subscription } from 'rxjs';
import { AuthService } from '../../service/auth-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-jobseeker-profile.component',
  standalone: false,
  templateUrl: './jobseeker-profile.component.html',
  styleUrl: './jobseeker-profile.component.css'
})
export class JobseekerProfileComponent {



  


  user: User | null = null;
  private subscription: Subscription = new Subscription();

  constructor(
    private authService: AuthService, // ✅ fixed spelling
    private router: Router

  ) { }

  ngOnInit(): void {

    console.log(this.authService.getToken());
    console.log(this.authService.getUserRole());
  
  }

  // loadUserProfile(): void {
  //   const sub = this.userSer.getUserProfile().subscribe({
  //     next: (res) => {
  //       console.log(res);
  //       if (res) {
  //         this.user = res;
  //       }
  //     },
  //     error: (err) => {
  //       console.error('Error loading user profile:', err);
  //     }
  //   });

  //   this.subscription.add(sub);
  // }

  // ngOnDestroy(): void {
  //   this.subscription.unsubscribe();
  // }

}

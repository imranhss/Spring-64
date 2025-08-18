import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../service/auth-service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login.component',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {


  loginForm!: FormGroup;
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  // onSubmit(): void {
  //   if (this.loginForm.invalid) {
  //     return;
  //   }

  //   const { email, password } = this.loginForm.value;

  //   this.authService.login(email, password).subscribe({
  //     next: (response) => {
  //       this.successMessage = 'Login successful!';
  //       this.errorMessage = null;
  //       this.router.navigate(['/jobsekpro']); // Redirect to home or another route after login
  //     },
  //     error: (err) => {
  //       this.errorMessage = 'Login failed. Please check your credentials.';
  //       this.successMessage = null;
  //     }
  //   });
  // }





  onSubmit(): void {
    if (this.loginForm.invalid) {
      return;
    }

    const { email, password } = this.loginForm.value;

    this.authService.login(email, password).subscribe({
      next: (response) => {
        this.successMessage = 'Login successful!';
        this.errorMessage = null;

        const role = this.authService.getUserRole();

        if (role === 'JOBSEEKER') {
          this.router.navigate(['/jobsekpro']);
        } else if (role === 'EMPLOYER') {
          this.router.navigate(['/empPprofile']);
        } else if (role === 'ADMIN') {
          this.router.navigate(['/admin-dashboard']);
        } else {
          this.router.navigate(['/']); // fallback
        }
      },
      error: (err) => {
        this.errorMessage = 'Login failed. Please check your credentials.';
        this.successMessage = null;
      }
    });
  }





}

import { NgModule, provideBrowserGlobalErrorListeners, provideZonelessChangeDetection } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Sidebar } from './layout/sidebar/sidebar';
import { Navbar } from './layout/navbar/navbar';
import { Footer } from './layout/footer/footer';
import { Dashboard } from './dashboard/dashboard/dashboard';
import { Addstudent } from './student/addstudent/addstudent';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, provideHttpClient, withFetch } from '@angular/common/http';
import { AlluserComponent } from './user/alluser.component/alluser.component';
import { AddjobseekerComponent } from './jobseekr/addjobseeker.component/addjobseeker.component';
import { LoginComponent } from './auth/login.component/login.component';
import { JobseekerProfileComponent } from './jobseeker/jobseeker-profile.component/jobseeker-profile.component';
import { AuthInterceptor } from './service/authInterceptor';

@NgModule({
  declarations: [
    App,
    Sidebar,
    Navbar,
    Footer,
    Dashboard,
    Addstudent,
    AlluserComponent,
    AddjobseekerComponent,
    LoginComponent,
    JobseekerProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZonelessChangeDetection(),
    provideClientHydration(withEventReplay()),
    provideHttpClient(
      withFetch()
    ),
     { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [App]
})
export class AppModule { }

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
import { PoliceStationComponent } from './policestation/police-station.component/police-station.component';
import { CountryComponent } from './country/country-component/country-component';
import { DivisionComponent } from './division/division-component/division-component';
import { DistrictComponent } from './district/district.component/district.component';
import { EmployerProfileComponent } from './employer/employer-profile.component/employer-profile.component';
import { LocationSelectorComponent } from './location/location-selector.component/location-selector.component';
import { AddressFormComponent } from './location/address-form.component/address-form.component';
import { AddressListComponent } from './location/address-list.component/address-list.component';
import { EmployerRegistration } from './employer/employer-registration/employer-registration';

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
    JobseekerProfileComponent,
    PoliceStationComponent,
    CountryComponent,
    DivisionComponent,
    DistrictComponent,
    EmployerProfileComponent,
    LocationSelectorComponent,
    AddressFormComponent,
    AddressListComponent,
    EmployerRegistration
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

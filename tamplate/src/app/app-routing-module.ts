import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Dashboard } from './dashboard/dashboard/dashboard';
import { Addstudent } from './student/addstudent/addstudent';
import { AlluserComponent } from './user/alluser.component/alluser.component';
import { AddjobseekerComponent } from './jobseekr/addjobseeker.component/addjobseeker.component';
import { JobseekerProfileComponent } from './jobseeker/jobseeker-profile.component/jobseeker-profile.component';
import { LoginComponent } from './auth/login.component/login.component';
import { PoliceStationComponent } from './policestation/police-station.component/police-station.component';
import { CountryComponent } from './country/country-component/country-component';
import { DivisionComponent } from './division/division-component/division-component';
import { DistrictComponent } from './district/district.component/district.component';
import { LocationSelectorComponent } from './location/location-selector.component/location-selector.component';
import { AddressFormComponent } from './location/address-form.component/address-form.component';
import { AddressListComponent } from './location/address-list.component/address-list.component';
import { EmployerRegistration } from './employer/employer-registration/employer-registration';
import { EmployerProfileComponent } from './employer/employer-profile.component/employer-profile.component';

const routes: Routes = [
  {path: '' , component:Addstudent},
  {path: 'allUser' , component:AlluserComponent},
  {path: 'addJobSeeker' , component:AddjobseekerComponent},
  {path: 'jobsekpro' , component:JobseekerProfileComponent},
  {path: 'login' , component:LoginComponent},
  {path: 'police' , component:PoliceStationComponent},
  {path: 'country' , component:CountryComponent},
  {path: 'division' , component:DivisionComponent},
  {path: 'district' , component:DistrictComponent},
  {path: 'location' , component:LocationSelectorComponent},
  {path: 'address' , component:AddressFormComponent},
  {path: 'addlist' , component:AddressListComponent},
  {path: 'empreg' , component:EmployerRegistration},
  {path: 'empPprofile' , component:EmployerProfileComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

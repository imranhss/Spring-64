import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Dashboard } from './dashboard/dashboard/dashboard';
import { Addstudent } from './student/addstudent/addstudent';
import { AlluserComponent } from './user/alluser.component/alluser.component';
import { AddjobseekerComponent } from './jobseekr/addjobseeker.component/addjobseeker.component';

const routes: Routes = [
  {path: '' , component:Addstudent},
  {path: 'allUser' , component:AlluserComponent},
  {path: 'addJobSeeker' , component:AddjobseekerComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

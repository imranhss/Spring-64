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
import { provideHttpClient, withFetch } from '@angular/common/http';
import { AlluserComponent } from './user/alluser.component/alluser.component';
import { AddjobseekerComponent } from './jobseekr/addjobseeker.component/addjobseeker.component';

@NgModule({
  declarations: [
    App,
    Sidebar,
    Navbar,
    Footer,
    Dashboard,
    Addstudent,
    AlluserComponent,
    AddjobseekerComponent
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
  ],
  bootstrap: [App]
})
export class AppModule { }

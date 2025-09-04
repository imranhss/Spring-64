import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App {
  protected title = 'tamplate';



showSidebar = true;

constructor(private router: Router) {
  router.events.subscribe(event => {
    // hide sidebar on certain routes
    if (router.url === '/login' || router.url === '/job-detail') {
      this.showSidebar = false;
    } else {
      this.showSidebar = true;
    }
  });
}

}

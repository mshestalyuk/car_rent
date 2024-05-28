import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service'; // Adjust the path as necessary

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  isLoggedIn: boolean;

  constructor(private router: Router, private authService: AuthService) { 
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  logInOrOut() {
    if (this.isLoggedIn) {
      this.authService.logOut();  // Assume logout method clears session and token
      this.router.navigate(['login']);
    } else {
      this.router.navigate(['login']);
    }
    this.isLoggedIn = this.authService.isLoggedIn();  // Update login status
  }
}

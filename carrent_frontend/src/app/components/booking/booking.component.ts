import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarService } from '../../services/car.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent  implements OnInit 
{

  cars: any[] = [];
  isLoggedIn: boolean;
  constructor(private router: Router, private carService: CarService, private authService:AuthService) { 
    this.isLoggedIn = this.authService.isLoggedIn();
  }
  ngOnInit(): void {
    this.carService.getAllCars().subscribe(data => {
      this.cars = data;
    });
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

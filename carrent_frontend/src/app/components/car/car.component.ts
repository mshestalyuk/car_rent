import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; // Import Router
import { CarService } from '../../services/car.service';
import { Car } from '../../interfaces/car';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {
  car: Car | undefined;
  isLoggedIn: boolean;

  constructor(
    private route: ActivatedRoute,
    private carService: CarService,
    private router: Router,
    private authService: AuthService
  ) {
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = +params['id'];
      this.carService.getCarById(id).subscribe(car => {
        this.car = car;
      }, error => {
        console.error('Error fetching car details:', error);
      });
    });
  }

  bookCar(carId: number): void {
    if (!carId) {
      alert('Error: No car selected.');
      return;
    }
    alert(`Booking process started for car ID: ${carId}`);
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

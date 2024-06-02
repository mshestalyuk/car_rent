import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; // Import Router
import { CarService } from '../../services/car.service';
import { Car } from '../../interfaces/car';
import { AuthService } from 'src/app/services/auth.service';
import { MatDialog } from '@angular/material/dialog';
import { BookingDialogComponent } from '../booking-dialog/booking-dialog.component';

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
    private dialog: MatDialog,
    private authService: AuthService
  ) {
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = +params['id']; // Make sure 'id' corresponds to the route configuration
      if (!id) {
        console.error('Invalid or missing car ID');
        return;
      }
      this.carService.getCarById(id).subscribe(car => {
        this.car = car;
      }, error => {
        console.error('Error fetching car details:', error);
      });
    });
  }
  

  bookCar(carId: number): void {
    const userId = this.authService.getUserIdFromToken(); // Get user ID from token
    const dialogRef = this.dialog.open(BookingDialogComponent, {
      width: '400px',
      data: { carId: carId, userId: userId }, // Pass both carId and userId
      panelClass: 'custom-dialog-container'
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log('Booking confirmed', result);
      }
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

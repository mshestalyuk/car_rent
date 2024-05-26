import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; // Import Router
import { CarService } from '../../services/car.service';
import { Car } from '../../interfaces/car';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {
  car: Car | undefined;

  constructor(
    private route: ActivatedRoute,
    private carService: CarService,
    private router: Router // Inject Router for navigation
  ) {}

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

  logOut(): void {
    // Implement your logout logic here
    console.error('Logout method not implemented.');
  }
}

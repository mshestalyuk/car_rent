import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarService } from '../../services/car.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent  implements OnInit 
{

  cars: any[] = [];
  constructor(private router: Router, private carService: CarService) { }
  ngOnInit(): void {
    this.carService.getAllCars().subscribe(data => {
      this.cars = data;
    });
  }
  logOut() {
      sessionStorage.clear();
      this.router.navigate(['login']);
    }

}

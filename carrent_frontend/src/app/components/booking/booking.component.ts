import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent {
  constructor(private router: Router) { }
logOut() {
    sessionStorage.clear();
    this.router.navigate(['login']);
  }

}

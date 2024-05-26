// src/app/components/profile/profile.component.ts
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookingService } from '../../services/booking.service';
import { Booking } from '../../interfaces/booking';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  bookings: Booking[] = [];

  constructor(
    private router: Router,
    private bookingService: BookingService
  ) {}

  ngOnInit(): void {
    this.loadUserBookings();
  }

  loadUserBookings(): void {
    // const userId = +sessionStorage.getItem('userId'); // Retrieve user ID from session storage
    const userId = 1;
    this.bookingService.getBookingsByUserId(userId).subscribe({
      next: (data) => {
        this.bookings = data;
      },
      error: (error) => {
        console.error('Error fetching bookings:', error);
      }
    });
  }

  logOut(): void {
    sessionStorage.clear();
    this.router.navigate(['login']);
  }
}

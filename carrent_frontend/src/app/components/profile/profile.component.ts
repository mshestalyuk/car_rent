// src/app/components/profile/profile.component.ts
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookingService } from '../../services/booking.service';
import { Booking } from '../../interfaces/booking';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  bookings: Booking[] = [];

  constructor(
    private router: Router,
    private bookingService: BookingService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadUserBookings();
  }

  loadUserBookings(): void {
    const userId = this.authService.getUserIdFromToken(); // Now using AuthService to get the user ID
    if (userId > 0) {
      this.bookingService.getBookingsByUserId(userId).subscribe({
        next: (data) => {
          this.bookings = data;
        },
        error: (error) => {
          console.error('Error fetching bookings:', error);
        }
      });
    } else {
      console.error('No valid user ID found');
    }
  }

  logOut(): void {
    this.authService.logOut();  // Assume logout method clears session and token
    this.router.navigate(['login']);
  }
}

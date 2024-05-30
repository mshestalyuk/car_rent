// src/app/components/profile/profile.component.ts

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookingService } from '../../services/booking.service';
import { Booking } from '../../interfaces/booking';
import { AuthService } from 'src/app/services/auth.service';
import { DriverLicenseService } from 'src/app/services/driverlicense.service';
import { License } from '../../interfaces/license';  // Import the interface

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
updateLicense() {
throw new Error('Method not implemented.');
}
  bookings: Booking[] = [];
  license: License | undefined;  // Use the License interface

  constructor(
    private router: Router,
    private bookingService: BookingService,
    private authService: AuthService,
    private licenseService: DriverLicenseService
  ) {}

  ngOnInit(): void {
    this.loadUserBookings();
    this.loadUserLicense();
  }

  loadUserBookings(): void {
    const userId = this.authService.getUserIdFromToken();
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

  loadUserLicense(): void {
    const userId = this.authService.getUserIdFromToken();
    if (userId > 0) {
      this.licenseService.getLicenseByUserId(userId).subscribe({
        next: (data) => {
          this.license = data;  // Store the whole license object
        },
        error: (error) => {
          console.error('Failed to fetch license', error);
        }
      });
    } else {
      console.error('No valid user ID found for license');
    }
  }

  logOut(): void {
    this.authService.logOut();
    this.router.navigate(['login']);
  }
}

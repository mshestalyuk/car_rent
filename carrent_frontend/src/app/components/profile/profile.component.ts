// src/app/components/profile/profile.component.ts

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookingService } from '../../services/booking.service';
import { Booking } from '../../interfaces/booking';
import { AuthService } from 'src/app/services/auth.service';
import { DriverLicenseService } from 'src/app/services/driverlicense.service';
import { License } from '../../interfaces/license';  // Import the interface
import { UserDetails } from 'src/app/interfaces/userdetails';
import { UserDetailsService } from 'src/app/services/userdetails.service';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/interfaces/auth';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  bookings: Booking[] = [];
  license: License | undefined;  // Use the License interface
  userDetails: UserDetails | undefined;  // Use the License interface
  user: User | undefined;  // Use the License interface

  creatingLicense: boolean = false;  // Track if creating a new license
  roleInfo: { id: number | null, name: string | null } = { id: null, name: null };

  constructor(
    private router: Router,
    private bookingService: BookingService,
    private authService: AuthService,
    private licenseService: DriverLicenseService,
    private userDetailsService: UserDetailsService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.loadUserBookings();
    this.loadUserLicense();
    this.loadUserProfile();
    this.loadUser();
    this.roleInfo = this.authService.getRoleFromToken();

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
          this.license = data;
          this.creatingLicense = false;  // License exists, no need to create one
        },
        error: (error) => {
          console.error('Failed to fetch license', error);
          this.creatingLicense = true;  // License not found, enable creation
        }
      });
    } else {
      console.error('No valid user ID found for license');
      this.creatingLicense = true;
    }
  }
  loadUserProfile(): void {
    const userId = this.authService.getUserIdFromToken();
    if (userId > 0) {
      this.userDetailsService.getUserDetailsByUserId(userId).subscribe({
        next: (data) => {
          this.userDetails = data;
        },
        error: (error) => {
          console.error('Error fetching user details:', error);
        }
      });
    }
  }

  loadUser(): void {
    const userId = this.authService.getUserIdFromToken();
    if (userId > 0) {
      this.userService.getUserByUserId(userId).subscribe({
        next: (data) => {
          this.user = data;
        },
        error: (error) => {
          console.error('Error fetching user details:', error);
        }
      });
    }
  }
  

  updateUser(): void {
    if (this.user && this.user.id) {
      this.userService.updateUser(this.user.id, this.user).subscribe({
        next: (updatedUser) => {
          console.log('User updated successfully:', updatedUser);
        },
        error: (error) => {
          console.error('Failed to update user:', error);
        }
      });
    } else {
      console.error('Invalid user data. Update cannot proceed.');
    }
  }
  
  navigateToAdmin() {
    this.router.navigate(['/admin']); // Make sure the path matches your routing configuration
  }
  // createLicense(inputData: License): void {
  //   // Assume LicenseInput is the correct interface for posting new license data
  //   this.licenseService.createLicense(inputData).subscribe({
  //     next: (data) => this.license = data,
  //     error: (error) => console.error('Error creating license:', error)
  //   });
  // }

  // createUserDetails(inputData: UserDetails): void {
  //   // Assume UserDetailsInput is the correct interface for posting new user details
  //   this.userDetailsService.createUserDetails(inputData).subscribe({
  //     next: (data) => this.userDetails = data,
  //     error: (error) => console.error('Error creating user details:', error)
  //   });
  // }

  logOut(): void {
    this.authService.logOut();
    this.router.navigate(['login']);
  }
}

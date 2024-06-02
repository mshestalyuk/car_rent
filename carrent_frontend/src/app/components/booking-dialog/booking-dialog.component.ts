import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { BookingService } from 'src/app/services/booking.service';
import { Booking } from 'src/app/interfaces/booking';
@Component({
  selector: 'app-booking-dialog',
  templateUrl: './booking-dialog.component.html',
  styleUrls: ['./booking-dialog.component.css']
})
export class BookingDialogComponent {
  
  
  constructor(
    public dialogRef: MatDialogRef<BookingDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private bookingService: BookingService
  ) {}

  
  onCancel(): void {
    this.dialogRef.close();
  }

  submitBooking(): void {
    if (!this.data.userId || !this.data.carId) {
      console.error('User ID or Car ID is missing.');
      return;
    }

    const bookingData: Booking = {
      userId: this.data.userId, // Passed from car component
      carId: this.data.carId, // Passed from car component
      pickUpLocId: this.data.pickUpLocId, // User input from dialog form
      dropOffLocId: this.data.dropOffLocId, // User input from dialog form
      pickUpDate: this.data.pickUpDate, // User input from dialog form
      dropOffDate: this.data.dropOffDate, // User input from dialog form
      pickOffDate: this.data.pickOffDate,
      status: 'pending'
    };
    this.bookingService.createBooking(bookingData).subscribe({
      next: (response) => {
        console.log('Booking created successfully:', response);
        this.dialogRef.close(true);
      },
      error: (error) => {
        console.error('Error creating booking:', error);
        this.dialogRef.close(false);
      }
    });
  }
  
  
  
}

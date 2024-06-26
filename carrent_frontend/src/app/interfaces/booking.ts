// src/app/models/booking.model.ts

export interface Booking {
    bookingId?: number; // Optional because it's generated by the database
    userId: number;
    carId: number;
    pickUpLocId: number;
    dropOffLocId: number;
    pickUpDate: string; // Using ISO string format for dates
    pickOffDate: string; // Date only, using ISO string format
    dropOffDate?: string; // Optional because it might not be set initially
    status: 'confirmed' | 'cancelled' | 'pending'; // Use a union type for status
  }
  
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Booking } from '../interfaces/booking'; // Define this model based on your data structure

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  private baseUrl = 'http://localhost:8080/api/v1/bookings';

  constructor(private http: HttpClient) { }

  getBookingsByUserId(userId: number): Observable<Booking[]> {
    return this.http.get<Booking[]>(`${this.baseUrl}/user/${userId}`);
  }

  createBooking(booking: Booking) {
    return this.http.post(`${this.baseUrl}`, booking);
  }
}

// src/app/services/car.service.ts

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Car } from '../interfaces/car'; // Import the Car model

@Injectable({
  providedIn: 'root'
})
export class CarService {
  private baseUrl = 'http://localhost:8080/api/v1/cars';

  constructor(private http: HttpClient) { }

  getAllCars(): Observable<Car[]> { // Use the Car model in the return type
    return this.http.get<Car[]>(`${this.baseUrl}/`);
  }

  getCarById(id: number): Observable<Car> {
    return this.http.get<Car>(`${this.baseUrl}/${id}`);
  }
}

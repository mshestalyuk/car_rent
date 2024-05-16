import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarService {
  private baseUrl = 'http://localhost:8080/api/v1/cars';

  constructor(private http: HttpClient) { }

  getAllCars(): Observable<any> {
    return this.http.get(`${this.baseUrl}/`);
  }
}

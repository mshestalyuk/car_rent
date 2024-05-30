// src/app/services/driverlicense.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { License } from '../interfaces/license';

@Injectable({
  providedIn: 'root'
})
export class DriverLicenseService {
  private baseUrl = 'http://localhost:8080/api/v1/licenses';

  constructor(private http: HttpClient) { }

  getLicenseByUserId(userId: number): Observable<License> {
    return this.http.get<License>(`${this.baseUrl}/by-user/${userId}`);
  }
}

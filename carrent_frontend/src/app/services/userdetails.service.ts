import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserDetails } from '../interfaces/userdetails';

@Injectable({
  providedIn: 'root'
})
export class UserDetailsService {
  private baseUrl = 'http://localhost:8080/api/v1/user-details';

  constructor(private http: HttpClient) { }

  getUserDetailsByUserId(userId: number): Observable<UserDetails> {
    return this.http.get<UserDetails>(`${this.baseUrl}/${userId}`);
  }
}

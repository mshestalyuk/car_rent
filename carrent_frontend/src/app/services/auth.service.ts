import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../interfaces/auth';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient) { }

  login(userDetails: { email: string, password: string }): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/authenticate`, userDetails)
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.jwt);  // Assuming 'jwt' is the key in the response containing the token
        })
      );
  }

  registerUser(userDetails: User) {
    return this.http.post(`${this.baseUrl}/users`, userDetails);
  }
}
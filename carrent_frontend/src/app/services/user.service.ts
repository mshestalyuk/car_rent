import { Injectable } from '@angular/core';
import { User } from '../interfaces/auth';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api/v1/users';

  constructor(private http: HttpClient) { }

  getUserByUserId(userId: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${userId}`);
  }
}

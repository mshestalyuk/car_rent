import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { User } from '../interfaces/auth';
import { jwtDecode } from "jwt-decode";
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
          localStorage.setItem('token', response.jwt);  // Storing the JWT token
        })
      );
  }

  registerUser(userDetails: User) {
    return this.http.post(`${this.baseUrl}/users`, userDetails);
  }

  isLoggedIn(): boolean {
    const token = localStorage.getItem('token');
    if (!token) return false;
  
    const decodedToken = this.decodeToken(token);
    if (!decodedToken || decodedToken.exp < Date.now() / 1000) {
      return false;  // Return false if token is expired
    }
    return true;
  }
  

  decodeToken(token: string): any {
    try {
      return jwtDecode(token);  // Correct usage of jwtDecode
    } catch (Error) {
      console.error('Error decoding token:', Error);
      return null;
    }
  }
  
  getUserIdFromToken(): number {
    const token = localStorage.getItem('token');
    if (token) {
      try {
        const decoded: any = jwtDecode(token);
        return +decoded.userId;  // Ensure you are now accessing 'userId' that we just added in backend.
      } catch (Error) {
        console.error('Failed to decode token:', Error);
        return 0;
      }
    }
    return 0; // Return a default or error value if no token or incorrect claim
  }

  getRoleFromToken(): { id: number | null, name: string | null } {
    const token = localStorage.getItem('token');
    if (token) {
      try {
        const decoded: any = jwtDecode(token);
        const id = decoded.roleId ? +decoded.roleId.idRole : null;
        const name = decoded.roleId ? decoded.roleId.roleName : null;
        return { id, name };
      } catch (Error) {
        console.error('Failed to decode token:', Error);
        return { id: null, name: null };
      }
    }
    return { id: null, name: null };
  }
  
  getRoleIdFromToken(): number | null {
    const token = localStorage.getItem('token');
    if (token) {
      try {
        const decoded: any = jwtDecode(token);
        return decoded.roleId ? +decoded.roleId.idRole : null;
      } catch (Error) {
        console.error('Failed to decode token:', Error);
        return null;
      }
    }
    return null;
  }

  logOut(): void {
    localStorage.removeItem('token');  // Removing the JWT token from local storage
  }
}

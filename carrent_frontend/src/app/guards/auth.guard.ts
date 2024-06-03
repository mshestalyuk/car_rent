import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const requiredRoles = next.data['roles'] as number[];

    if (this.authService.isLoggedIn()) {
      if (requiredRoles) {
        const userRoleId = this.authService.getRoleIdFromToken();

        if (userRoleId !== null) {
          // Now checking against a number since getRoleIdFromToken has been updated
          if (requiredRoles.includes(userRoleId)) {
            return true;
          } else {
            this.router.navigate(['/unauthorized']); // Redirect if role is not authorized
            return false;
          }
        } else {
          this.router.navigate(['/unauthorized']); // Redirect if role ID could not be obtained
          return false;
        }
      }
      return true; // If no roles are required by the route, access is granted
    } else {
      this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
      return false;
    }
  }
}

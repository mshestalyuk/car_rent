import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {
  users: any[] = [];
  isLoggedIn: boolean | undefined;

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.fetchUsers();
  }

  fetchUsers() {
    this.userService.getUsers().subscribe({
      next: (users) => this.users = users,
      error: (error) => console.error('Error fetching users', error)
    });
  }

  editUser(user: any) {
    console.log('Editing user:', user);
    // Implement your edit logic here, possibly opening a modal or redirecting to an edit page
  }

  deleteUser(userId: number) {
    this.userService.deleteUser(userId).subscribe({
      next: () => {
        console.log('Deleted user with ID:', userId);
        this.fetchUsers();  // Refresh the list after deletion
      },
      error: (error) => console.error('Error deleting user', error)
    });
  }

  logInOrOut() {
    if (this.isLoggedIn) {
      this.authService.logOut();
      this.router.navigate(['login']);
    } else {
      this.router.navigate(['login']);
    }
    this.isLoggedIn = this.authService.isLoggedIn();
  }
}

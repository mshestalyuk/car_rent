import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required]
  })

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private msgService: MessageService
  ) { }

  get email() {
    return this.loginForm.controls['email'];
  }
  get password() { return this.loginForm.controls['password']; }

  loginUser() {
    if (this.loginForm.valid) {
      const email = this.loginForm.get('email')!.value as string;
      const password = this.loginForm.get('password')!.value as string;
  
      this.authService.login({ email, password }).subscribe(
        () => {
          // Navigate to the profile page instead of the home page
          this.router.navigate(['/profile']); // Update this line to redirect to the profile page
          this.msgService.add({ severity: 'success', summary: 'Success', detail: 'Logged in successfully' });
        },
        error => {
          this.msgService.add({ severity: 'error', summary: 'Login Failed', detail: 'Invalid email or password' });
        }
      );
    }
  }
  
  
}

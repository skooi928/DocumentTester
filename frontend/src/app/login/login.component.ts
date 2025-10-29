import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';  // ✅ add this
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  template: `
    <div class="card" style="max-width:420px; margin:48px auto">
      <h2>Login</h2>
      <form (submit)="submit($event)">
        <label>Username</label>
        <input [(ngModel)]="username" name="username" required />
        <label>Password</label>
        <input [(ngModel)]="password" name="password" type="password" required />
        <div style="margin-top:12px">
          <button type="submit">Login</button>
        </div>
        <p *ngIf="error" style="color:crimson">{{error}}</p>
      </form>
    </div>
  `
})
export class LoginComponent {
  username = '';
  password = '';
  error = '';

  constructor(private auth: AuthService, private router: Router) {}

  submit(e: Event) {
    e.preventDefault();
    if (this.auth.login(this.username, this.password)) {
      this.router.navigate(['/dashboard']);
    } else {
      this.error = 'Invalid';
    }
  }
}

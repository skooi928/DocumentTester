import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private key = 'deposit_auth_token';

  login(username: string, password: string): boolean {
    if (username && password) {
      localStorage.setItem(this.key, 'mock-token');
      return true;
    }
    return false;
  }

  logout() {
    localStorage.removeItem(this.key);
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem(this.key);
  }
}

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';  // ✅ add this
import { ApiService } from '../services/api.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-deposit-form',
  templateUrl: './deposit-form.component.html',  // ✅ external HTML file
  styleUrls: ['./deposit-form.component.css'],
  standalone: true,               // ✅ standalone component
  imports: [CommonModule, FormsModule],  // ✅ include FormsModule here
})
export class DepositFormComponent {
  clientId: number | null = null;
  principalAmount: number | null = null;
  startDate = '';
  maturityDate = '';
  message = '';
  error = '';

  constructor(private api: ApiService, private router: Router) {}

  onSubmit(e: Event) {
    e.preventDefault();
    const payload = {
      clientId: this.clientId,
      principalAmount: this.principalAmount,
      startDate: this.startDate,
      maturityDate: this.maturityDate
    };
    this.api.createDeposit(payload).subscribe({
      next: () => { this.message = 'Created'; this.router.navigate(['/dashboard']); },
      error: () => { this.error = 'Failed'; }
    });
  }
}

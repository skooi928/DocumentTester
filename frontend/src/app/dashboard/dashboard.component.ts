import { Component, OnInit } from '@angular/core';
import { ApiService, Deposit } from '../services/api.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  template: `
    <div class="card">
      <h2>Deposits</h2>
      <table>
        <thead><tr><th>ID</th><th>Client</th><th>Principal</th><th>Accrued</th><th>Start</th><th>Maturity</th><th>Status</th></tr></thead>
        <tbody>
          <tr *ngFor="let d of deposits">
            <td>{{d.depositId}}</td>
            <td>{{d.clientId}}</td>
            <td>{{d.principalAmount}}</td>
            <td>{{d.accumulatedInterest}}</td>
            <td>{{d.startDate}}</td>
            <td>{{d.maturityDate}}</td>
            <td>{{d.status}}</td>
          </tr>
        </tbody>
      </table>
    </div>
  `,
  imports: []
})
export class DashboardComponent implements OnInit {
  deposits: Deposit[] = [];
  constructor(private api: ApiService) {}
  ngOnInit() {
    this.api.getDeposits().subscribe(res => this.deposits = res);
  }
}

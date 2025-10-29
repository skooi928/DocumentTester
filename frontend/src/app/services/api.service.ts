import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_BASE = 'http://localhost:8080/api';

export interface Deposit {
  depositId?: number;
  clientId?: number;
  principalAmount?: number;
  accumulatedInterest?: number;
  startDate?: string;
  maturityDate?: string;
  status?: string;
}

@Injectable({ providedIn: 'root' })
export class ApiService {
  constructor(private http: HttpClient) {}

  getDeposits(): Observable<Deposit[]> {
    return this.http.get<Deposit[]>(`${API_BASE}/deposits`);
  }

  createDeposit(body: any) {
    return this.http.post(`${API_BASE}/deposits`, body);
  }

  getRate() {
    return this.http.get(`${API_BASE}/deposits/rate`);
  }
}

package com.example.deposit.entity;

import javax.persistence.*;

@Entity
@Table(name = "interest_rates")
public class InterestRate {

    @Id
    @Column(name = "rate_id")
    private Long rateId;

    @Column(name = "annual_rate", precision = 10, scale = 6)
    private Double annualRate;

    public InterestRate() {}

    public Long getRateId() { return rateId; }
    public void setRateId(Long rateId) { this.rateId = rateId; }
    public Double getAnnualRate() { return annualRate; }
    public void setAnnualRate(Double annualRate) { this.annualRate = annualRate; }
}

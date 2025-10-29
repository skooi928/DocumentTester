package com.example.deposit.dao;

import com.example.deposit.entity.InterestRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRateRepository extends JpaRepository<InterestRate, Long> {
}

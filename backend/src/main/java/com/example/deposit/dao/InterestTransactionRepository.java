package com.example.deposit.dao;

import com.example.deposit.entity.InterestTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestTransactionRepository extends JpaRepository<InterestTransaction, Long> {
}

package com.example.deposit.dao;

import com.example.deposit.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findByStatus(String status);
}

package com.example.deposit.service;

import com.example.deposit.dao.DepositRepository;
import com.example.deposit.dao.InterestRateRepository;
import com.example.deposit.dao.InterestTransactionRepository;
import com.example.deposit.entity.Deposit;
import com.example.deposit.entity.InterestRate;
import com.example.deposit.entity.InterestTransaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class DepositService {

    private final DepositRepository depositRepository;
    private final InterestRateRepository interestRateRepository;
    private final InterestTransactionRepository transactionRepository;

    public DepositService(DepositRepository depositRepository,
                          InterestRateRepository interestRateRepository,
                          InterestTransactionRepository transactionRepository) {
        this.depositRepository = depositRepository;
        this.interestRateRepository = interestRateRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<Deposit> listDeposits() {
        return depositRepository.findAll();
    }

    public BigDecimal calculateInterest(Long depositId) {
        Deposit d = depositRepository.findById(depositId).orElse(null);
        if (d == null) return BigDecimal.ZERO;
        InterestRate rate = interestRateRepository.findAll().stream().findFirst().orElse(null);
        if (rate == null) return BigDecimal.ZERO;
        BigDecimal dailyRate = BigDecimal.valueOf(rate.getAnnualRate()).divide(BigDecimal.valueOf(365), 10, RoundingMode.HALF_UP);
        BigDecimal interest = d.getPrincipalAmount().multiply(dailyRate);
        return interest.setScale(4, RoundingMode.HALF_UP);
    }

    @Transactional
    public void creditInterest(Long depositId, BigDecimal amount) {
        Deposit d = depositRepository.findById(depositId).orElse(null);
        if (d == null) return;
        if (d.getAccumulatedInterest() == null) d.setAccumulatedInterest(BigDecimal.ZERO);
        d.setAccumulatedInterest(d.getAccumulatedInterest().add(amount));
        depositRepository.save(d);

        InterestTransaction tx = new InterestTransaction();
        tx.setDepositId(depositId);
        tx.setInterestAmount(amount);
        tx.setTransactionDate(LocalDate.now());
        transactionRepository.save(tx);
    }

    public InterestRate getInterestRate() {
        return interestRateRepository.findAll().stream().findFirst().orElse(null);
    }

    public Deposit placeDeposit(Deposit deposit) {
        if (deposit.getAccumulatedInterest() == null) deposit.setAccumulatedInterest(BigDecimal.ZERO);
        if (deposit.getStartDate() == null) deposit.setStartDate(LocalDate.now());
        deposit.setStatus("ACTIVE");
        return depositRepository.save(deposit);
    }

    public Deposit updateDeposit(Long depositId, Deposit newDeposit) {
        return depositRepository.findById(depositId)
                .map(d -> {
                    if (newDeposit.getPrincipalAmount() != null) d.setPrincipalAmount(newDeposit.getPrincipalAmount());
                    if (newDeposit.getMaturityDate() != null) d.setMaturityDate(newDeposit.getMaturityDate());
                    return depositRepository.save(d);
                })
                .orElse(null);
    }
}

package com.example.deposit.jobs;

import com.example.deposit.dao.DepositRepository;
import com.example.deposit.service.DepositService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DailyInterestCalculationJob implements Job {

    private final DepositRepository depositRepository;
    private final DepositService depositService;

    public DailyInterestCalculationJob(DepositRepository depositRepository, DepositService depositService) {
        this.depositRepository = depositRepository;
        this.depositService = depositService;
    }

    @Override
    public void execute(JobExecutionContext context) {
        depositRepository.findByStatus("ACTIVE").forEach(d -> {
            BigDecimal interest = depositService.calculateInterest(d.getDepositId());
            depositService.creditInterest(d.getDepositId(), interest);
        });
    }
}

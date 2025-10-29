package com.example.deposit.jobs;

import com.example.deposit.dao.DepositRepository;
import com.example.deposit.entity.Deposit;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MaturityInterestCreditJob implements Job {

    private final DepositRepository depositRepository;

    public MaturityInterestCreditJob(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @Override
    public void execute(JobExecutionContext context) {
        for (Deposit d : depositRepository.findByStatus("ACTIVE")) {
            if (d.getMaturityDate() != null && d.getMaturityDate().isEqual(LocalDate.now())) {
                d.setStatus("MATURED");
                depositRepository.save(d);
            }
        }
    }
}

package com.example.deposit.config;

import com.example.deposit.jobs.DailyInterestCalculationJob;
import com.example.deposit.jobs.MaturityInterestCreditJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail dailyInterestCalculationJobDetail() {
        return JobBuilder.newJob(DailyInterestCalculationJob.class)
                .withIdentity("dailyInterestCalculationJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger dailyInterestCalculationJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(dailyInterestCalculationJobDetail())
                .withIdentity("dailyInterestCalculationTrigger")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(0, 0))
                .build();
    }

    @Bean
    public JobDetail maturityInterestCreditJobDetail() {
        return JobBuilder.newJob(MaturityInterestCreditJob.class)
                .withIdentity("maturityInterestCreditJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger maturityInterestCreditJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(maturityInterestCreditJobDetail())
                .withIdentity("maturityInterestCreditTrigger")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(6, 0))
                .build();
    }
}

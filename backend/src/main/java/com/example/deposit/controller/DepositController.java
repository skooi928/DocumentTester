package com.example.deposit.controller;

import com.example.deposit.entity.Deposit;
import com.example.deposit.entity.InterestRate;
import com.example.deposit.service.DepositService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/deposits")
@CrossOrigin(origins = "*")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping
    public List<Deposit> listDeposits() {
        return depositService.listDeposits();
    }

    @GetMapping("/{depositId}/interest")
    public BigDecimal calculateDepositInterest(@PathVariable Long depositId) {
        return depositService.calculateInterest(depositId);
    }

    @GetMapping("/rate")
    public InterestRate getCurrentInterestRate() {
        return depositService.getInterestRate();
    }

    @PostMapping
    public Deposit placeDeposit(@RequestBody Deposit deposit) {
        return depositService.placeDeposit(deposit);
    }

    @PutMapping("/{depositId}")
    public Deposit updateDeposit(@PathVariable Long depositId, @RequestBody Deposit deposit) {
        return depositService.updateDeposit(depositId, deposit);
    }
}

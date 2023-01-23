package com.example.budget.controllers;

import com.example.budget.services.BudgetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/daily")
    public  int dailyBudget() {
        return budgetService.getDailyBudget();
    }

    @GetMapping("/balance")
    public int balance() {
        return budgetService.getBalance();
    }
}

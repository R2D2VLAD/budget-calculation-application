package com.example.budget.services;

public interface BudgetService {
    int getDailyBudget();

    int getBalance();

    int getVacationBonus(int daysCount);

    int getSalaryWithVacation(int vacationDaysCount, int vacationWorkingDaysCount, int workingDaysInMonth);
}

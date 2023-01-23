package com.example.budget.services.impl;

import com.example.budget.model.Transaction;
import com.example.budget.services.BudgetService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class BudgetServiceImpl implements BudgetService {

    public static final int SALARY = 35_000;
    public static final int SAVING = 5_000;
    public static final int DAYLI_BUDGET = (SALARY - SAVING) / LocalDate.now().lengthOfMonth();

    public static int balance = 0;

    public static final double AVG_DAYS = 29.3;

    private static Map<Month, Map<Long, Transaction>> transactions = new TreeMap<>();

    public static long lastId = 0;

    @Override
    public int getDailyBudget() {
        return DAYLI_BUDGET;
    }

    @Override
    public int getBalance() {
        return SALARY - SAVING - getAllSpend();
    }
    @Override
    public long addTransaction(Transaction transaction) {
        Map<Long, Transaction> monthTransactions = transactions.getOrDefault(LocalDate.now().getMonth(), new LinkedHashMap<>());
        monthTransactions.put(lastId, transaction);
        transactions.put(LocalDate.now().getMonth(), monthTransactions);
        return lastId++;
    }
    @Override
    public Transaction getTransaction(long id) {
        for (Map<Long, Transaction> transactionsByMonth : transactions.values()) {
            Transaction transaction = transactionsByMonth.get(id);
            if (transaction != null) {
                return transaction;
            }
        }
        return null;
    }
    @Override
    public Transaction editTransaction(long id, Transaction transaction) {
        for (Map<Long, Transaction> transactionsByMonth : transactions.values()) {
            if (transactionsByMonth.containsKey(id)) {
                transactionsByMonth.put(id, transaction);
                return transaction;
            }
        }
        return null;
    }
    @Override
    public boolean deleteTransaction(long id) {
        for (Map<Long, Transaction> transactionsByMonth : transactions.values()) {
            if (transactionsByMonth.containsKey(id)) {
                transactionsByMonth.remove(id);
                return true;
            }
        }
        return false;
    }
    @Override
    public void deleteAllTransaction() {
        transactions = new TreeMap<>();
    }

    @Override
    public int getDayliBalance() {
        return DAYLI_BUDGET * LocalDate.now().getDayOfMonth() - getAllSpend();
    }
    @Override
    public int getAllSpend() {
        Map<Long, Transaction> monthTransactions = transactions.getOrDefault(LocalDate.now().getMonth(), new LinkedHashMap<>());
        int sum = 0;
        for (Transaction transaction : monthTransactions.values()) {
            sum += transaction.getSum();
        }
        return sum;
    }

    @Override
    public int getVacationBonus(int daysCount) {
        double avgDaySalary = SALARY/AVG_DAYS;
        return (int) (daysCount * avgDaySalary);
    }
    @Override
    public int getSalaryWithVacation(int vacationDaysCount, int vacationWorkingDaysCount, int workingDaysInMonth) {
        int salary = SALARY / workingDaysInMonth * (workingDaysInMonth - vacationWorkingDaysCount);
        return salary + getVacationBonus(vacationDaysCount);
    }
}

package com.hotelms.expense.service;

import com.hotelms.expense.dto.DashboardSummary;
import com.hotelms.expense.model.Expense;
import com.hotelms.expense.model.TransactionType;
import com.hotelms.expense.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    // Create a new transaction
    public Expense createExpense(Expense expense) {
        if (expense.getDate() == null) {
            expense.setDate(LocalDate.now());
        }
        return expenseRepository.save(expense);
    }

    // Fetch all transactions
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAllByOrderByDateDesc();
    }

    // Fetch an entry by ID
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with ID: " + id));
    }

    // Modify an existing transaction
    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense existing = getExpenseById(id);
        existing.setDescription(updatedExpense.getDescription());
        existing.setAmount(updatedExpense.getAmount());
        existing.setCategory(updatedExpense.getCategory());
        existing.setDate(updatedExpense.getDate() != null ? updatedExpense.getDate() : LocalDate.now());
        existing.setTransactionType(updatedExpense.getTransactionType());
        return expenseRepository.save(existing);
    }

    // Remove an entry
    public void deleteExpense(Long id) {
        Expense existing = getExpenseById(id);
        expenseRepository.delete(existing);
    }

    // Filter transactions by category
    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategoryIgnoreCase(category);
    }

    // Filter transactions by date range
    public List<Expense> getExpensesByDateRange(LocalDate start, LocalDate end) {
        return expenseRepository.findByDateBetweenOrderByDateDesc(start, end);
    }

    // Filter transactions by month and year
    public List<Expense> getExpensesByMonthAndYear(int month, int year) {
        return expenseRepository.findByMonthAndYear(month, year);
    }

    // Core Analytics: Aggregates data to compile the dashboard analytics summary
    public DashboardSummary getDashboardSummary() {
        List<Expense> allTransactions = expenseRepository.findAll();

        double totalIncome = 0;
        double totalExpenses = 0;
        Map<String, Double> categoryBreakdown = new HashMap<>();

        for (Expense transaction : allTransactions) {
            double amount = transaction.getAmount();
            if (transaction.getTransactionType() == TransactionType.INCOME) {
                totalIncome += amount;
            } else if (transaction.getTransactionType() == TransactionType.EXPENSE) {
                totalExpenses += amount;
                
                // Aggregate expenses by category
                String category = transaction.getCategory();
                // Ensure uniform casing for matching
                String displayCategory = category.substring(0, 1).toUpperCase() + category.substring(1).toLowerCase();
                categoryBreakdown.put(displayCategory, categoryBreakdown.getOrDefault(displayCategory, 0.0) + amount);
            }
        }

        double netBalance = totalIncome - totalExpenses;

        // Round values to 2 decimal places to prevent floating-point precision issues
        totalIncome = round(totalIncome);
        totalExpenses = round(totalExpenses);
        netBalance = round(netBalance);
        
        for (Map.Entry<String, Double> entry : categoryBreakdown.entrySet()) {
            categoryBreakdown.put(entry.getKey(), round(entry.getValue()));
        }

        return new DashboardSummary(totalIncome, totalExpenses, netBalance, categoryBreakdown);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}

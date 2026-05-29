package com.hotelms.expense.controller;

import com.hotelms.expense.dto.DashboardSummary;
import com.hotelms.expense.model.Expense;
import com.hotelms.expense.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    // POST /api/expenses -> Log a new transaction
    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        Expense created = expenseService.createExpense(expense);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET /api/expenses -> Fetch transactions (supports optional filtering by category, date range, or month/year)
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer year) {

        if (category != null && !category.trim().isEmpty()) {
            return ResponseEntity.ok(expenseService.getExpensesByCategory(category));
        } else if (startDate != null && endDate != null) {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            return ResponseEntity.ok(expenseService.getExpensesByDateRange(start, end));
        } else if (month != null && year != null) {
            return ResponseEntity.ok(expenseService.getExpensesByMonthAndYear(month, year));
        }

        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    // GET /api/expenses/report -> Get dashboard analytics summary
    @GetMapping("/report")
    public ResponseEntity<DashboardSummary> getDashboardReport() {
        return ResponseEntity.ok(expenseService.getDashboardSummary());
    }

    // PUT /api/expenses/{id} -> Modify an entry
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        Expense updated = expenseService.updateExpense(id, expense);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/expenses/{id} -> Remove an entry
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}

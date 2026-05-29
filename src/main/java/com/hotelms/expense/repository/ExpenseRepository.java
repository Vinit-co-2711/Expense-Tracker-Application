package com.hotelms.expense.repository;

import com.hotelms.expense.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Filter records by category
    List<Expense> findByCategoryIgnoreCase(String category);

    // Look up entries within a specific date range
    List<Expense> findByDateBetweenOrderByDateDesc(LocalDate startDate, LocalDate endDate);

    // Filter by month/year
    @Query("SELECT e FROM Expense e WHERE YEAR(e.date) = :year AND MONTH(e.date) = :month ORDER BY e.date DESC")
    List<Expense> findByMonthAndYear(@Param("month") int month, @Param("year") int year);

    // Fetch all transactions ordered by date descending
    List<Expense> findAllByOrderByDateDesc();
}

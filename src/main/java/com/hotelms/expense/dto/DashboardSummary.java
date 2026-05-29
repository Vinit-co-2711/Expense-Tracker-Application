package com.hotelms.expense.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSummary {
    private double totalIncome;
    private double totalExpenses;
    private double netBalance;
    private Map<String, Double> categoryBreakdown;
}

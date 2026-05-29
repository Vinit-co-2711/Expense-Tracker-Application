package com.hotelms.expense;

import com.hotelms.expense.model.Expense;
import com.hotelms.expense.model.TransactionType;
import com.hotelms.expense.repository.ExpenseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ExpenseApplication {

    public static void main(String[] eloquenceArgs) {
        SpringApplication.run(ExpenseApplication.class, eloquenceArgs);
    }

    @Bean
    public CommandLineRunner initData(ExpenseRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                LocalDate today = LocalDate.now();
                
                List<Expense> initialData = List.of(
                        Expense.builder()
                                .description("Monthly Salary")
                                .amount(4800.00)
                                .category("Salary")
                                .date(today.minusDays(20))
                                .transactionType(TransactionType.INCOME)
                                .build(),
                        Expense.builder()
                                .description("Apartment Rent")
                                .amount(1200.00)
                                .category("Rent")
                                .date(today.minusDays(18))
                                .transactionType(TransactionType.EXPENSE)
                                .build(),
                        Expense.builder()
                                .description("Organic Groceries")
                                .amount(184.50)
                                .category("Food")
                                .date(today.minusDays(15))
                                .transactionType(TransactionType.EXPENSE)
                                .build(),
                        Expense.builder()
                                .description("Car Refuel")
                                .amount(65.00)
                                .category("Transport")
                                .date(today.minusDays(12))
                                .transactionType(TransactionType.EXPENSE)
                                .build(),
                        Expense.builder()
                                .description("Freelance UI Design")
                                .amount(950.00)
                                .category("Freelance")
                                .date(today.minusDays(10))
                                .transactionType(TransactionType.INCOME)
                                .build(),
                        Expense.builder()
                                .description("Cinema tickets & popcorn")
                                .amount(42.00)
                                .category("Entertainment")
                                .date(today.minusDays(8))
                                .transactionType(TransactionType.EXPENSE)
                                .build(),
                        Expense.builder()
                                .description("Restaurant Dinner")
                                .amount(112.80)
                                .category("Food")
                                .date(today.minusDays(5))
                                .transactionType(TransactionType.EXPENSE)
                                .build(),
                        Expense.builder()
                                .description("Electricity & Water Bill")
                                .amount(210.40)
                                .category("Utilities")
                                .date(today.minusDays(2))
                                .transactionType(TransactionType.EXPENSE)
                                .build()
                );
                
                repository.saveAll(initialData);
                System.out.println(">> Database initialized with sample expense and income records.");
            }
        };
    }
}

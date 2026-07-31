package com.expense.smartexpensetracker.service;

import com.expense.smartexpensetracker.model.Expense;
import com.expense.smartexpensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    private Long nextId = 1L;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Expense addExpense(Expense expense) {
        expense.setId(nextId++);
        return repository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }

    public List<Expense> getExpensesByCategory(String category) {
        return repository.findByCategory(category);
    }

    public double getTotalExpenses() {
        return repository.findAll()
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public double getTotalByCategory(String category) {
        return repository.findByCategory(category)
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public boolean deleteExpense(Long id) {

        Optional<Expense> expense = repository.findById(id);

        if (expense.isPresent()) {
            repository.delete(expense.get());
            return true;
        }

        return false;
    }
}
package com.expense.smartexpensetracker.service;

import com.expense.smartexpensetracker.exception.ExpenseNotFoundException;
import com.expense.smartexpensetracker.model.Expense;
import com.expense.smartexpensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteExpense(Long id) {
        Expense expense = repository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense with ID " + id + " not found"));

        repository.delete(expense);
    }
    public double getMonthlyTotal(int year, int month) {
        return repository.findAll()
                .stream()
                .filter(expense -> expense.getDate().getYear() == year)
                .filter(expense -> expense.getDate().getMonthValue() == month)
                .mapToDouble(Expense::getAmount)
                .sum();
    }
    public void clearExpenses() {
        repository.clear();
        nextId = 1L;
    }

}
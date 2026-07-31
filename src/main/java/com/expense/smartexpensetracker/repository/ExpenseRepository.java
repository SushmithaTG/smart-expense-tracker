package com.expense.smartexpensetracker.repository;

import com.expense.smartexpensetracker.model.Expense;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ExpenseRepository {

    private final List<Expense> expenses = new ArrayList<>();

    public Expense save(Expense expense) {
        expenses.add(expense);
        return expense;
    }

    public List<Expense> findAll() {
        return new ArrayList<>(expenses);
    }

    public Optional<Expense> findById(Long id) {
        return expenses.stream()
                .filter(expense -> expense.getId().equals(id))
                .findFirst();
    }

    public List<Expense> findByCategory(String category) {
        return expenses.stream()
                .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public void delete(Expense expense) {
        expenses.remove(expense);
    }
    public void clear() {
        expenses.clear();
    }
}
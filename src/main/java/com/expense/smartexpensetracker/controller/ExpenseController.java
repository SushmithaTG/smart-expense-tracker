package com.expense.smartexpensetracker.controller;

import com.expense.smartexpensetracker.dto.TotalResponse;
import com.expense.smartexpensetracker.model.Expense;
import com.expense.smartexpensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Expense> addExpense(@Valid @RequestBody Expense expense) {
        Expense savedExpense = service.addExpense(expense);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return ResponseEntity.ok(service.getAllExpenses());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Expense>> getExpensesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(service.getExpensesByCategory(category));
    }

    @GetMapping("/total")
    public ResponseEntity<TotalResponse> getTotalExpenses() {
        return ResponseEntity.ok(new TotalResponse(service.getTotalExpenses()));
    }
    @GetMapping("/summary/{year}/{month}")
    public ResponseEntity<TotalResponse> getMonthlyTotal(
            @PathVariable int year,
            @PathVariable int month) {

        return ResponseEntity.ok(
                new TotalResponse(service.getMonthlyTotal(year, month))
        );
    }

    @GetMapping("/total/{category}")
    public ResponseEntity<TotalResponse> getTotalByCategory(@PathVariable String category) {
        return ResponseEntity.ok(new TotalResponse(service.getTotalByCategory(category)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        service.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
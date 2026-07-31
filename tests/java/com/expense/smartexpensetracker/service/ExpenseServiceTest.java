package com.expense.smartexpensetracker.service;

import com.expense.smartexpensetracker.model.Expense;
import com.expense.smartexpensetracker.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.expense.smartexpensetracker.exception.ExpenseNotFoundException;

class ExpenseServiceTest {

    @Mock
    private ExpenseRepository repository;

    @InjectMocks
    private ExpenseService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddExpense() {

        Expense expense = new Expense(
                "Pizza",
                250.0,
                "Food",
                LocalDate.now()
        );

        when(repository.save(expense)).thenReturn(expense);

        Expense result = service.addExpense(expense);

        assertNotNull(result);
        assertEquals("Pizza", result.getTitle());
        assertEquals(250.0, result.getAmount());
        assertEquals("Food", result.getCategory());

        verify(repository).save(expense);
    }

    @Test
    void shouldGetAllExpenses() {

        Expense expense = new Expense(
                "Book",
                500.0,
                "Education",
                LocalDate.now()
        );

        when(repository.findAll()).thenReturn(List.of(expense));

        List<Expense> result = service.getAllExpenses();

        assertEquals(1, result.size());
        assertEquals("Book", result.get(0).getTitle());

        verify(repository).findAll();
    }

    @Test
    void shouldGetExpensesByCategory() {

        Expense expense = new Expense(
                "Pizza",
                250.0,
                "Food",
                LocalDate.now()
        );

        when(repository.findByCategory("Food"))
                .thenReturn(List.of(expense));

        List<Expense> result = service.getExpensesByCategory("Food");

        assertEquals(1, result.size());
        assertEquals("Food", result.get(0).getCategory());

        verify(repository).findByCategory("Food");
    }

    @Test
    void shouldCalculateTotalExpenses() {

        Expense expense1 = new Expense(
                "Pizza",
                250.0,
                "Food",
                LocalDate.now()
        );

        Expense expense2 = new Expense(
                "Book",
                500.0,
                "Education",
                LocalDate.now()
        );

        when(repository.findAll())
                .thenReturn(List.of(expense1, expense2));

        double total = service.getTotalExpenses();

        assertEquals(750.0, total);

        verify(repository).findAll();
    }

    @Test
    void shouldCalculateTotalByCategory() {

        Expense expense1 = new Expense(
                "Pizza",
                250.0,
                "Food",
                LocalDate.now()
        );

        Expense expense2 = new Expense(
                "Coffee",
                100.0,
                "Food",
                LocalDate.now()
        );

        when(repository.findByCategory("Food"))
                .thenReturn(List.of(expense1, expense2));

        double total = service.getTotalByCategory("Food");

        assertEquals(350.0, total);

        verify(repository).findByCategory("Food");
    }

    @Test
    void shouldDeleteExpense() {

        Expense expense = new Expense(
                "Coffee",
                100.0,
                "Food",
                LocalDate.now()
        );

        expense.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(expense));

        service.deleteExpense(1L);

        verify(repository).findById(1L);
        verify(repository).delete(expense);
    }
    @Test
    void shouldThrowExceptionWhenExpenseNotFound() {

        when(repository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                ExpenseNotFoundException.class,
                () -> service.deleteExpense(999L)
        );

        verify(repository).findById(999L);
    }
    @Test
    void shouldCalculateMonthlyTotal() {

        Expense expense1 = new Expense(
                "Pizza",
                250.0,
                "Food",
                LocalDate.of(2026, 7, 10)
        );

        Expense expense2 = new Expense(
                "Movie",
                300.0,
                "Entertainment",
                LocalDate.of(2026, 7, 20)
        );

        Expense expense3 = new Expense(
                "Books",
                500.0,
                "Education",
                LocalDate.of(2026, 8, 5)
        );

        when(repository.findAll())
                .thenReturn(List.of(expense1, expense2, expense3));

        double total = service.getMonthlyTotal(2026, 7);

        assertEquals(550.0, total);

        verify(repository).findAll();
    }
}
package com.expense.smartexpensetracker.controller;

import com.expense.smartexpensetracker.model.Expense;
import com.expense.smartexpensetracker.service.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;





@SpringBootTest
@AutoConfigureMockMvc
class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ExpenseService service;

    @BeforeEach
    void setup() {
        service.clearExpenses();
    }

    @Test
    @DisplayName("Should add a new expense successfully")
    void shouldAddExpense() throws Exception {

        Expense expense = new Expense(
                "Pizza",
                250.0,
                "Food",
                LocalDate.of(2026,7,31)
        );

        mockMvc.perform(post("/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expense)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Pizza"))
                .andExpect(jsonPath("$.amount").value(250.0))
                .andExpect(jsonPath("$.category").value("Food"));
    }
    @Test
    @DisplayName("Should return all expenses")
    void shouldReturnAllExpenses() throws Exception {

        Expense expense = new Expense(
                "Book",
                500.0,
                "Education",
                LocalDate.now()
        );

        mockMvc.perform(post("/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expense)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/expenses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Book"))
                .andExpect(jsonPath("$[0].amount").value(500.0))
                .andExpect(jsonPath("$[0].category").value("Education"));
    }
    @Test
    @DisplayName("Should return expenses by category")
    void shouldReturnExpensesByCategory() throws Exception {

        Expense expense = new Expense(
                "Pizza",
                250.0,
                "Food",
                LocalDate.now()
        );

        mockMvc.perform(post("/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expense)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/expenses/category/Food"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Pizza"))
                .andExpect(jsonPath("$[0].amount").value(250.0))
                .andExpect(jsonPath("$[0].category").value("Food"));
    }
    @Test
    @DisplayName("Should calculate total expenses")
    void shouldReturnTotalExpenses() throws Exception {

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

        mockMvc.perform(post("/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expense1)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expense2)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/expenses/total"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(750.0));
    }
    @Test
    @DisplayName("Should calculate total expenses by category")
    void shouldReturnTotalByCategory() throws Exception {

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

        mockMvc.perform(post("/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expense1)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expense2)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/expenses/total/Food"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(350.0));
    }

    @Test
    @DisplayName("Should delete an expense")
    void shouldDeleteExpense() throws Exception {

        Expense expense = new Expense(
                "Coffee",
                100.0,
                "Food",
                LocalDate.now()
        );

        String response = mockMvc.perform(post("/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expense)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Expense savedExpense = objectMapper.readValue(response, Expense.class);

        mockMvc.perform(delete("/expenses/" + savedExpense.getId()))
                .andExpect(status().isNoContent());
    }
    @Test
    @DisplayName("Should return 404 when expense does not exist")
    void shouldReturnNotFoundForInvalidId() throws Exception {

        mockMvc.perform(delete("/expenses/999"))
                .andExpect(status().isNotFound());
    }
    @Test
    @DisplayName("Should reject invalid expense")
    void shouldRejectInvalidExpense() throws Exception {

        String json = """
            {
                "title":"",
                "amount":-100,
                "category":"",
                "date":null
            }
            """;

        mockMvc.perform(post("/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("Should calculate monthly total")
    void shouldGetMonthlyTotal() throws Exception {

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

        mockMvc.perform(post("/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expense1)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expense2)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expense3)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/expenses/monthly")
                        .param("year", "2026")
                        .param("month", "7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(550.0));
    }

}

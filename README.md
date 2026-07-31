# Smart Expense Tracker 💰

A Spring Boot REST API for managing and tracking personal expenses.

## 🚀 Features

- Add a new expense
- View all expenses
- Find expenses by category
- Calculate total expenses
- Calculate total expenses by category
- Delete an expense
- Input validation
- Global exception handling
- Unit testing
- Controller/API testing

## 🛠️ Technologies Used

- Java 17
- Spring Boot 3.5.4
- Spring Web
- Spring Validation
- JUnit 5
- Mockito
- Maven
- Git & GitHub

## 📂 Project Structure

```text
src
├── main
│   └── java
│       └── com.expense.smartexpensetracker
│           ├── controller
│           ├── dto
│           ├── exception
│           ├── model
│           ├── repository
│           └── service
│
└── test
    └── java
        └── com.expense.smartexpensetracker
            ├── controller
            └── service
            
 ```
            
## 🔗 API Endpoints
| Method | Endpoint                        | Description              |
| ------ | ------------------------------- | ------------------------ |
| POST   | `/expenses`                     | Add an expense           |
| GET    | `/expenses`                     | Get all expenses         |
| GET    | `/expenses/category/{category}` | Get expenses by category |
| GET    | `/expenses/total`               | Get total expenses       |
| GET    | `/expenses/total/{category}`    | Get total by category    |
| DELETE | `/expenses/{id}`                | Delete an expense        |


## 📝 Example Request

### Add Expense

```json
{
  "title": "Pizza",
  "amount": 250.0,
  "category": "Food",
  "date": "2026-07-31"
}
```

Example Response

```json
{
  "id": 1,
  "title": "Pizza",
  "amount": 250.0,
  "category": "Food",
  "date": "2026-07-31"
}
```

## ▶️ How to Run

### Clone the repository

```bash
git clone https://github.com/SushmithaTG/smart-expense-tracker.git
```

### Go to the project

```bash
cd smart-expense-tracker
```

### Run the application On Windows:

```bash
.\mvnw.cmd spring-boot:run
```

### Run the application On Linux/macOS:

```bash
./mvnw spring-boot:run
```

### The application will run at:
### http://localhost:8080


## 🧪 Run Tests

### On Windows:

```bash
.\mvnw.cmd test
```

### The project includes:

- Controller tests using MockMvc
- Service unit tests using JUnit 5
- Mockito-based repository mocking
- Validation tests
- Exception handling tests

## 📌 Project Status

BUILD SUCCESS ✅

The Smart Expense Tracker REST API is implemented and tested successfully.

## 👩‍💻 Author

Sushmitha TG
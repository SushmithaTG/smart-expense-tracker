# AI Notes

## How I Used AI

I used ChatGPT as a development assistant while building the Smart Expense Tracker API.

AI assistance was used for:

- Spring Boot project structure
- REST controller implementation
- Service-layer logic
- Exception handling
- Input validation
- JUnit 5 and Mockito test cases
- MockMvc controller tests
- Debugging Java and Maven errors
- Reviewing and improving project documentation

## AI-Generated vs. My Work

I used AI to generate initial drafts and suggestions for:

- `ExpenseController`
- `ExpenseService`
- `GlobalExceptionHandler`
- `ExpenseNotFoundException`
- JUnit and Mockito test classes

I was responsible for reviewing, integrating, and modifying the generated code to fit the assignment requirements and the final project structure.

I decided the final endpoint design, including:

- `/expenses`
- `/expenses/category/{category}`
- `/expenses/total`
- `/expenses/total/{category}`
- `/expenses/{id}`

I also reviewed the validation rules, exception handling, service logic, and test cases before using them.

During development, I fixed compilation and import issues related to `ExpenseNotFoundException` after the exception class was placed in the `exception` package.

I also adjusted the validation logic so invalid expenses, such as negative amounts or empty required fields, are rejected.

## What I Validated and Changed

I reviewed the AI-generated suggestions and tested the implementation locally.

I specifically validated:

- Adding expenses
- Retrieving all expenses
- Filtering expenses by category
- Calculating total expenses
- Calculating total expenses by category
- Deleting expenses
- Handling invalid expense input
- Handling non-existent expense IDs
- Controller/API behavior using MockMvc
- Service behavior using JUnit 5 and Mockito

I fixed compilation and test issues during development and verified that the complete test suite passes using the Maven Wrapper.

The test command used was:

```powershell
.\mvnw.cmd test
```
The test suite completed successfully with:

- Tests run: 8
- Failures: 0
- Errors: 0
- Skipped: 0

## AI Suggestions I Did Not Use

I considered several suggestions during development that I decided not to include.

**1. Database integration**
AI suggested that the project could use a database such as MySQL or H2. I did not add a database because the assignment explicitly allows in-memory storage or a local JSON file and states that a database is not required. Keeping the application in memory also keeps the implementation simple and focused on the required REST API functionality.

**2. Additional bonus functionality**
AI suggested adding optional features such as search functionality or Swagger/OpenAPI documentation. I did not add an optional bonus feature because the required functionality was the priority, and the assignment states that bonus functionality is optional.

**3. Changing the endpoint design**
AI suggested using query parameters such as `/expenses?category=Food` for category filtering. I decided to keep category filtering as path-variable endpoints instead — `/expenses/category/{category}` and `/expenses/total/{category}` — because this design is simple, readable, and works correctly for the required functionality.
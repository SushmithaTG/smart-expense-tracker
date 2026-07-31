# AI Notes

## How I Used AI

I used ChatGPT as a development assistant while building the Smart Expense
Tracker API.

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

I used AI to help generate and review parts of the implementation, including
initial code suggestions for the controller, service logic, exception
handling, and test cases.

I was responsible for integrating the suggestions into my project, reviewing
the code, fixing errors, and verifying that the implementation matched the
assignment requirements.

I also made decisions about the API structure, project organization,
validation, exception handling, and testing approach.

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

During development, I fixed compilation and test issues, including an
exception package/import issue.

I used the Maven Wrapper to run the test suite:

```powershell
.\mvnw.cmd test
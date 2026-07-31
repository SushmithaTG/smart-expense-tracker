# Test Suite

The automated test suite is implemented using Maven's standard
`src/test/java` directory.

Tests include:

- Controller/API tests using MockMvc
- Service unit tests using JUnit 5
- Mockito repository mocking
- Validation tests
- Exception handling tests

The tests can be executed from the project root with:

```bash
./mvnw test
```
### On Windows:

```bash
.\mvnw.cmd test
```
### On Linux/macOS:

```bash
./mvnw test
```



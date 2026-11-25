# BDD API Testing

Backend services use **Cucumber + RestAssured** for BDD-style API tests.

## Structure

Each service has:
- `src/test/java/.../bdd/RunCucumberBDDTest.java` — Cucumber JUnit Platform Suite runner
- `src/test/java/.../bdd/*Steps.java` — Step definitions (Given/When/Then) using RestAssured
- `src/test/resources/features/*.feature` — Gherkin feature files

## Run BDD tests

```bash
# Runs all unit + BDD tests for a service
mvn -pl ms-patients-service test

# Only the Cucumber suite
mvn -pl ms-patients-service test -Dtest=RunCucumberBDDTest
```

## Adding a new scenario

1. Add a scenario to the relevant `.feature` file
2. Implement the Given/When/Then steps in a `*Steps.java` class
3. RestAssured base URI defaults to `$PATIENTS_SERVICE_URL` or `http://localhost:8080`

## Dependencies (from parent BOM)

- `cucumber-java`, `cucumber-junit-platform-engine`, `cucumber-spring` (7.33.0)
- `rest-assured` (5.5.0)
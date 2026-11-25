# Medical Centre Acceptance Tests

This module contains end-to-end acceptance tests using Playwright for the Medical Centre system.

## Prerequisites

All services must be running via Docker Compose:

```bash
docker-compose up -d kafka mongodb keycloak
docker-compose up -d ms-patients-service ms-medics-service ms-appointments-service ms-notification-service medical-centre-ui
```

## Run Tests

```bash
cd acceptance-test
mvn test
```

## Test Structure

- `src/test/java/com/ew/medicalcentre/acceptance/` — Playwright E2E tests
- `src/test/resources/features/` — Gherkin feature files (BDD)
- `src/test/resources/testdata/` — Test data files

## Playwright Configuration

Playwright is configured to run headless by default. Set `HEADLESS=false` to see browser during debug:

```bash
HEADLESS=false mvn test
```
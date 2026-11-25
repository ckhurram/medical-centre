# ADR-005: Keycloak for OAuth2/OIDC Authentication

## Status
Proposed

## Date
2026-11-25

## Decision Makers
Architecture Team

## Context
The system needs authentication and authorization for:
- Patients registering and booking appointments
- Admins managing medics and appointments
- Service-to-service authentication

## Decision
Use Keycloak as the identity provider with OAuth2/OIDC:
- Realms: `medical-centre` with clients for each service
- Roles: `PATIENT`, `ADMIN`
- Services validate JWTs as OAuth2 Resource Servers
- Keycloak realm export in `deploy/keycloak/realm-export.json`

## Consequences

### Positive
- Centralized user management
- Standard OAuth2/OIDC protocol
- Role-based access control out of the box
- Self-hosted, no vendor lock-in
- Mature ecosystem and Spring Boot integration

### Negative
- Additional infrastructure component to operate
- Token management complexity (refresh, revocation)
- Learning curve for Keycloak configuration

## Alternatives Considered
- **Auth0 / AWS Cognito**: Rejected — want self-hosted, no vendor lock-in
- **Custom auth service**: Rejected — reinventing the wheel, security risk
- **Spring Security with in-memory users**: Rejected — not production-ready
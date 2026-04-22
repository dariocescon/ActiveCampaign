# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

Spring Boot 4.0 / Java 21 integration service for the [ActiveCampaign API](https://developers.activecampaign.com/reference/overview).

## Commands

```bash
# Build
./mvnw clean package

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=ClassName

# Run the application
./mvnw spring-boot:run

# Build Docker image
./mvnw spring-boot:build-image
```

## Architecture

The project is in its early stage. The single entry point is `ActiveCampaignIntegrationApplication` (standard `@SpringBootApplication`). Configuration lives in `src/main/resources/application.properties`.

Main package: `com.aton.proj`

## Code style

- Keep code linear and easy to read — this codebase is maintained by junior Java developers.
- Default to Java 21 unless a different version is specified.
- Propose solutions rather than making unilateral design decisions.

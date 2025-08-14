# Room Reservation System

This is a semester project for SWA(2024), built as a set of microservices for reserving rooms.

## What’s inside?

- **user-service**: Handles user registration and info.
- **reservation-service**: Lets users reserve rooms.
- **mail-service**: Sends emails (like registration confirmations).
- **service-discovery**: Eureka for finding services.
- **integration-tests**: Automated tests for the whole system.
- **doc**: Diagrams and notes.

## Architecture

The project uses a microservice architecture.  
Each service is independent and communicates over REST APIs.  
Service discovery is handled by Eureka, and services can scale individually.  
See [doc/diagram.png](doc/diagram.png) for a quick overview.

## CI/CD

We use GitLab CI/CD for automated builds and tests.  
Each push triggers pipelines that build, test, and (optionally) deploy the services using the `.gitlab-ci.yml` files in each service folder.

## How to run

You’ll need Docker and Java 17.  
To start everything, just run:

```sh
docker-compose up --build
```

Or run each service with Maven:

```sh
cd user-service
./mvnw spring-boot:run
```
(Do the same for other services.)

## How to test

Integration tests are in [root-repo/integration-tests](root-repo/integration-tests).  
Run them with:

```sh
cd root-repo/integration-tests
./mvnw test
```

## Notes

- This is a prototype, not production-ready.

---

## Authors
Matěj Pošta and Lukáš Novák

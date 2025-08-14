# Room Reservation System

This is a semester project for SWA(2024), built as a set of microservices for reserving rooms.

## What’s inside?

- **user-service**: Handles user registration and info.
- **reservation-service**: Lets users reserve rooms.
- **mail-service**: Sends emails (like registration confirmations).
- **service-discovery**: Eureka for finding services.
- **root**: Build of whole system and automated tests for it.
- **doc**: Diagrams and notes.

## Architecture

The project uses a microservice architecture.  
Each service is independent and communicates over REST APIs.  
Service discovery is handled by Eureka, and services can scale individually.  
See [doc/diagram.png](doc/diagram.png) for a quick overview.

## CI/CD

This project was devoloped with GitLab CI/CD for automated builds and tests.  
Each push triggers pipelines that build, test, and (optionally) deploy the services using the `.gitlab-ci.yml` files in each service folder.

Of course, nowadays on Github this pipeline is not working.

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
- This project is moved from CTU FEE gitlab.

---

## Authors
Matěj Pošta and Lukáš Novák

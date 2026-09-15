# GuestHouse Review Service

A backend microservice built with Spring Boot and Docker, designed for managing room reviews and ratings.

## Microservices
This service operates independently with its own database (`reviewservice`) and exposes a REST API. It connects with the **Booking Service** to verify whether a customer has actually booked a room before allowing them to submit a review.

## Features
* **Review Management:** Full REST API endpoints for creating, viewing, and deleting reviews.
* **Input Validation:** Built-in validation ensuring ratings are between 1 and 5 and comments are not left blank.
* **Cross-Service Verification:** Verifies with the Booking Service that a valid booking exists for the customer and room prior to saving a review.

## Tech Stack
* Java 21
* Spring Boot 4.0.6
* Spring Data JPA & Hibernate
* MySQL
* Docker

## Repository Structure Note for Docker Compose
For `docker compose up --build` to locate all service directories correctly using the relative build contexts, ensure that all three repositories (`GuestHouse-Booking-System`, `GuestHouse-Customer-Service`, `GuestHouse-Review-Service`) and your infrastructure repository (`GuestHouse-Infrastructure`) are placed within the same parent folder like this:

```text
📁 parent-folder/
├── 📁 GuestHouse-Infrastructure/  (contains docker-compose.yml)
├── 📁 GuestHouse-Booking-System/
├── 📁 GuestHouse-Customer-Service/
└── 📁 GuestHouse-Review-Service/

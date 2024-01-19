# EMS (Event Management System) Monorepo

## Overview

This monorepo contains all the microservices that make up the Event Management System (EMS). EMS is designed to offer a comprehensive solution for event management, encompassing user authentication, data management, API gateway, and a frontend interface.

Due to university reasons I had to develop the same architecture in monolith [here](https://github.com/kanakx/ems-api-spring.git).

## Microservices

1. **API Gateway**: Serves as the central entry point for the EMS, routing requests to the appropriate microservices through authentication filter.

2. **Auth Service**: Manages user authentication and authorization using JWT tokens.

3. **Data Service**: Handles the storage, processing, and retrieval of business data.

4. **React Frontend**: Provides the user interface for event browsing, login, registration, and administration.

# Technologies and tools

- Spring Boot
- Spring Security
- Hibernate
- JWT
- PostreSQL
- MySQL
- Docker
# EMS (Event Management System) Monorepo

## Overview

This monorepo contains all the microservices that make up the Event Management System (EMS). EMS is designed to offer a simple solution for event management, encompassing user authentication, data management, and an API gateway. 

Due to university reasons I had to develop the same architecture in monolith [here](https://github.com/kanakx/ems-api-spring.git).

## Backend microservices

- **API Gateway**: Serves as the central entry point for the EMS, routing requests to the appropriate microservices through authentication filter.

- **Auth Service**: Manages user authentication and authorization using JWT tokens.

- **Data Service**: Handles the storage, processing, and retrieval of business data.

## Frontend

[**React Frontend**](https://github.com/kanakx/ems-react.git): Provides the user interface for event browsing, login, registration, and administration.

## Technologies and tools

- Spring Boot
- Spring Security
- Hibernate
- PostreSQL
- MySQL
- Docker
- JWT

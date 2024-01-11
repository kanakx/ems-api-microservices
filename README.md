# EMS API Gateway

## Overview

This project is an implementation of widely known API Gateway pattern. It is intended to provide efficient handling of
requests, secure authentication, and smooth integration with microservices architecture. In this case, I decided to
utilize Spring Cloud Gateway due to its seamless integration with Spring ecosystem and ease of use.

## Key Features

- **Filters:** Implements custom filters for request validation and authentication, ensuring secure access to the EMS
  system.
- **Load Balancing** Efficiently distributes incoming network traffic across a group of backend servers to ensure high
  availability and reliability.

## Related Services

The gateway serves a role of an entry point to two further services:

- [AuthService](https://github.com/kanakx/ems-auth-service-spring) - Handles user authentication and authorization
  through JWT tokens
- [DataService](https://github.com/kanakx/ems-data-service-spring) - Manages the business data layer

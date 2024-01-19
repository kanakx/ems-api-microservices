# EMS Auth Service

## Overview

The EMS Auth Service is a crucial component of the Event Management System, focusing on user authentication and
authorization. It utilizes JWT tokens to ensure secure and efficient user management within the system.

## Key Features

- **User Identity Verification:** Verifies user credentials and issues tokens upon successful authentication.
- **Token-Based Authentication:** Utilizes JWT for secure and stateless authentication.
- **Access Control:** Manages user roles and permissions, ensuring users have the correct level of access.

## Related Services

- [API Gateway](https://github.com/kanakx/ems-api-gateway-spring) - Serves as the entry point, routing requests to
  appropriate services.
- [DataService](https://github.com/kanakx/ems-data-service-spring) - Manages the business data layer
- [React Frontend Layer](https://github.com/kanakx/event-management-react) - The interactive user interface for event browsing, registration, and administration.

# Eureka Server for Microservices Discovery

## Overview
This project is a simple implementation of a Eureka Server for service discovery in a microservices application.

## Key Features
- **Centralized Service Registry:** Maintains a registry of all microservices, allowing for easy discovery and management of service instances.
- **Service Health Monitoring:** Automatically detects the status of each service instance, ensuring only healthy instances are used for requests.

## Related Services
Explore the microservices registered with this Eureka Server:

1. **[API Gateway](https://github.com/kanakx/ems-api-gateway-spring)** - Serves as the entry point, routing requests to appropriate services.
2. **[Auth Service](https://github.com/kanakx/ems-auth-service-spring)** - Handles user authentication and authorization through JWT tokens
3. **[Data Service](https://github.com/kanakx/ems-data-service-spring)** - Manages the business data layer

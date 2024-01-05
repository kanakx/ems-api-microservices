package com.ems.emsapigatewayspring.filters;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    private RouteValidator() {}

    public static final List<String> endpoints = List.of(
            "/api/v1/auth/register",
            "/api/v1/auth/login",
            "/api/v1/auth/validate",
            "/eureka"
    );

    public static final Predicate<ServerHttpRequest> isSecured =
            serverHttpRequest -> endpoints
                    .stream()
                    .noneMatch(uri -> serverHttpRequest
                            .getURI()
                            .getPath()
                            .contains(uri)
                    );

}

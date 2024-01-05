package com.ems.emsapigatewayspring.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final RouteValidator routeValidator;
    private final WebClient webClient;

    public AuthFilter(RouteValidator routeValidator, WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.routeValidator = routeValidator;
        this.webClient = webClientBuilder.build();
    }

    private Mono<Void> authenticate(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authToken = extractAuthToken(exchange.getRequest());
        TokenDto tokenDto = TokenDto.builder().token(authToken).build();

        return webClient.post()
                .uri("lb://EMS-AUTH-SERVICE-SPRING/api/v1/auth/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(tokenDto)
                .retrieve()
                .bodyToMono(TokenValidationResponseDto.class)
                .flatMap(response -> {
                    if (response.getIsValid() != null && response.getIsValid()) {
                        return chain.filter(exchange);
                    } else {
                        return handleUnauthenticated(exchange, "Invalid Token");
                    }
                })
                .onErrorResume(e -> handleUnauthenticated(exchange, "AuthService Error: " + e.getMessage()));
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                return authenticate(exchange, chain);
            }
            return chain.filter(exchange);
        };
    }

    private String extractAuthToken(ServerHttpRequest request) {
        List<String> headers = request.getHeaders().get(HttpHeaders.AUTHORIZATION);
        if (headers == null || headers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No auth header!");
        }

        String authHeader = headers.getFirst();
        if (authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid auth header!");
    }

    private Mono<Void> handleUnauthenticated(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

        // Prepare a JSON response
        ObjectMapper mapper = new ObjectMapper();
        String body;
        try {
            body = mapper.writeValueAsString(Map.of("error", message));
        } catch (Exception e) {
            body = "{\"error\":\"Error processing the error message\"}";
        }

        // Write the response
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);

        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

    @NoArgsConstructor
    public static class Config {

    }

}

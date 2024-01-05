package com.ems.emsapigatewayspring.filters;

import com.ems.emsapigatewayspring.exceptions.ExceptionContent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
                    if (Boolean.TRUE.equals(response.getIsValid())) {
                        return chain.filter(exchange);
                    } else {
                        return handleUnauthenticated(exchange, "Invalid Token");
                    }
                })
                .onErrorResume(e -> {
                    if (e instanceof WebClientResponseException webClientResponseException) {
                        return handleUnauthenticated(exchange, webClientResponseException.getResponseBodyAsString());
                    } else {
                        return handleUnauthenticated(exchange, "AuthService Error: " + e.getMessage());
                    }
                });
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

    private Mono<Void> handleUnauthenticated(ServerWebExchange exchange, String jsonMessage) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

        String errorMessage;
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(jsonMessage);
            errorMessage = jsonNode.has("message") ? jsonNode.get("message").asText() : jsonMessage;
        } catch (IOException e) {
            errorMessage = jsonMessage;
        }

        ExceptionContent exceptionContent = ExceptionContent.builder()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message(errorMessage)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String body;
        try {
            body = mapper.writeValueAsString(exceptionContent);
        } catch (Exception e) {
            body = "{\"error\":\"Error processing the error message\"}";
        }

        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);

        return exchange.getResponse().writeWith(Mono.just(buffer));
    }


    @NoArgsConstructor
    public static class Config {

    }

}

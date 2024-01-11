package com.ems.emsapigatewayspring.filters;

import com.ems.emsapigatewayspring.exceptions.ExceptionContent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);
    private final WebClient webClient;

    public AuthFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClient = webClientBuilder.build();
    }

    private Mono<Void> authenticate(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Authenticating request: {}", exchange.getRequest().getPath());
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
                        logger.info("Token validated successfully for request: {}", exchange.getRequest().getPath());
                        return chain.filter(exchange);
                    } else {
                        logger.warn("Invalid token for request: {}", exchange.getRequest().getPath());
                        return handleUnauthenticated(exchange, "Invalid Token");
                    }
                })
                .onErrorResume(e -> {
                    logger.error("Error during authentication: {}", e.getMessage(), e);
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
            if (RouteValidator.isSecured.test(exchange.getRequest())) {
                return authenticate(exchange, chain);
            }
            logger.info("Bypassing auth for unsecured route: {}", exchange.getRequest().getPath());
            return chain.filter(exchange);
        };
    }

    private String extractAuthToken(ServerHttpRequest request) {
        List<String> headers = request.getHeaders().get(HttpHeaders.AUTHORIZATION);
        if (headers == null || headers.isEmpty()) {
            logger.warn("No auth header in request: {}", request.getPath());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No auth header!");
        }

        String authHeader = headers.getFirst();
        if (authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        logger.warn("Invalid auth header in request: {}", request.getPath());
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid auth header!");
    }

    private Mono<Void> handleUnauthenticated(ServerWebExchange exchange, String jsonMessage) {
        logger.warn("Handling unauthenticated request: {}", exchange.getRequest().getPath());
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

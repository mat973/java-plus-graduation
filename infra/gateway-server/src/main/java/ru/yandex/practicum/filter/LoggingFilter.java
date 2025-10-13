package ru.yandex.practicum.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long startTime = System.currentTimeMillis();
        ServerHttpRequest request = exchange.getRequest();

        // Логируем входящий запрос
        log.info("=== GATEWAY INCOMING REQUEST ===");
        log.info("Method: {}", request.getMethod());
        log.info("Path: {}", request.getPath());
        log.info("URI: {}", request.getURI());
        log.info("Headers: {}", request.getHeaders());
        log.info("QueryParams: {}", request.getQueryParams());
        log.info("Remote Address: {}", request.getRemoteAddress());

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            long duration = System.currentTimeMillis() - startTime;
            ServerHttpResponse response = exchange.getResponse();

            // Логируем ответ
            log.info("=== GATEWAY RESPONSE ===");
            log.info("Status: {}", response.getStatusCode());
            log.info("Duration: {}ms", duration);
            log.info("Response Headers: {}", response.getHeaders());
            log.info("=====================================");
        }));
    }
}

package ru.yandex.practicum.filter;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class LoggingFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        long startTime = System.currentTimeMillis();

        return DataBufferUtils.join(request.getBody())
                .defaultIfEmpty(exchange.getResponse().bufferFactory().wrap(new byte[0]))
                .flatMap(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);

                    String requestBody = new String(bytes, StandardCharsets.UTF_8);
                    log.info("=== GATEWAY INCOMING REQUEST ===");
                    log.info("Method: {}", request.getMethod());
                    log.info("Path: {}", request.getPath());
                    log.info("URI: {}", request.getURI());
                    log.info("Headers: {}", request.getHeaders());
                    log.info("QueryParams: {}", request.getQueryParams());
                    log.info("Remote Address: {}", request.getRemoteAddress());
                    log.info("Request Body: {}", requestBody.isBlank() ? "<empty>" : requestBody);

                    // Создаём новый request с тем же телом
                    ServerHttpRequest mutatedRequest = request.mutate().build();
                    mutatedRequest = new ServerHttpRequestDecorator(mutatedRequest) {
                        @Override
                        public Flux<DataBuffer> getBody() {
                            return Flux.just(exchange.getResponse()
                                    .bufferFactory()
                                    .wrap(bytes));
                        }
                    };

                    // Копируем response, чтобы можно было прочитать body при ответе
                    ServerHttpResponse originalResponse = exchange.getResponse();
                    DataBufferFactory bufferFactory = originalResponse.bufferFactory();
                    ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                        @Override
                        public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            return super.writeWith(
                                    fluxBody.map(dataBuffer -> {
                                        byte[] content = new byte[dataBuffer.readableByteCount()];
                                        dataBuffer.read(content);
                                        DataBufferUtils.release(dataBuffer);

                                        String responseBody = new String(content, StandardCharsets.UTF_8);
                                        long duration = System.currentTimeMillis() - startTime;
                                        log.info("=== GATEWAY RESPONSE ===");
                                        log.info("Status: {}", getStatusCode());
                                        log.info("Duration: {}ms", duration);
                                        log.info("Response Headers: {}", getHeaders());
                                        log.info("Response Body: {}", responseBody.isBlank() ? "<empty>" : responseBody);
                                        log.info("=====================================");
                                        return bufferFactory.wrap(content);
                                    })
                            );
                        }
                    };

                    // Передаём дальше с новым request и response
                    return chain.filter(exchange.mutate()
                            .request(mutatedRequest)
                            .response(decoratedResponse)
                            .build());
                });
    }
}

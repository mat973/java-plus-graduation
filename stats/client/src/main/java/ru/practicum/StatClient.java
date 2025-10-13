package ru.practicum;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.practicum.dto.RequestHitDto;
import ru.practicum.dto.ResponseDto;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
public class StatClient {
    private final DiscoveryClient discoveryClient;
    private RestClient client;
    private volatile boolean initialized = false;
    private final Object lock = new Object();

    public StatClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    private void ensureInitialized() {
        if (!initialized) {
            synchronized (lock) {
                if (!initialized) {
                    List<ServiceInstance> instances = discoveryClient.getInstances("STATS-SERVICE");
                    if (instances == null || instances.isEmpty()) {
                        throw new IllegalStateException("STATS-SERVICE не найден в DiscoveryClient");
                    }
                    ServiceInstance instance = instances.get(0);
                    this.client = RestClient.builder()
                            .baseUrl(instance.getUri().toString())
                            .build();
                    this.initialized = true;
                    log.info("StatClient инициализирован для URL: {}", instance.getUri());
                }
            }
        }
    }

    public void sendHit(RequestHitDto hit) {
        ensureInitialized();
        log.info("Вызов записи хита в клиенте");
        try {
            client.post()
                    .uri("/hit")
                    .contentType(APPLICATION_JSON)
                    .body(hit).retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            log.info("Ошибка при записи статистики: {}", e.getMessage());
        }
    }

    public List<ResponseDto> getStats(String start, String end, List<String> uris, boolean unique) {
        ensureInitialized();
        log.info("Вызов получения статистики в клиенте");
        try {
            client.get()
                    .uri(uriBuilder -> uriBuilder.path("/stats")
                            .queryParam("start", start)
                            .queryParam("end", end)
                            .queryParam("uris", uris)
                            .queryParam("unique", unique)
                            .build())
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<ResponseDto>>() {
                    });
        } catch (Exception e) {
            log.info("Ошибка при выводе статистики: {}", e.getMessage());
        }
        return Collections.emptyList();
    }
}
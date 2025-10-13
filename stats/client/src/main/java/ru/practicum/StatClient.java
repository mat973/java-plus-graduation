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
    private final RestClient client;

    public StatClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        this.client = RestClient.builder()
                .baseUrl(getServiceUrl("STATS-SERVICE"))
                .build();
    }

    private String getServiceUrl(String serviceName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (instances == null || instances.isEmpty()) {
            log.error("Сервис {} не найден через DiscoveryClient", serviceName);
            throw new IllegalStateException("Сервис " + serviceName + " не найден");
        }
        ServiceInstance instance = instances.get(0);
        String url = instance.getUri().toString();
        log.info("Найден сервис {} по адресу {}", serviceName, url);
        return url;
    }

    public void sendHit(RequestHitDto hit) {
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
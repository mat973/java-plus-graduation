package ru.practicum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.practicum.feign.request.FeingLikeRequestClient;
import ru.practicum.feign.user.FeignUserClient;

@SpringBootApplication
@EnableFeignClients(clients = {FeignUserClient.class, FeingLikeRequestClient.class})
public class EventService {
    public static void main(String[] args) {
        SpringApplication.run(EventService.class, args);
    }
}

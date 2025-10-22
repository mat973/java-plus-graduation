package ru.practicum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.practicum.feign.coment.FeignCommentClient;
import ru.practicum.feign.request.FeignRequestClient;
import ru.practicum.feign.user.FeignUserClient;

@SpringBootApplication
@EnableFeignClients(clients = {FeignUserClient.class})
public class EventService {
    public static void main(String[] args) {
        SpringApplication.run(EventService.class, args);
    }
}

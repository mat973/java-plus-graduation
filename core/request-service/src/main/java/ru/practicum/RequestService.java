package ru.practicum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.practicum.feign.event.FeignEventClient;
import ru.practicum.feign.user.FeignUserClient;

@SpringBootApplication
@EnableFeignClients(clients = {FeignUserClient.class, FeignEventClient.class})
public class RequestService {
    public static void main(String[] args) {
        SpringApplication.run(RequestService.class, args);
    }
}

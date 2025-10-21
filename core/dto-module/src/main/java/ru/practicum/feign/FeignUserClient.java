package ru.practicum.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "USER-CLIENT")
public interface FeignUserClient {
}

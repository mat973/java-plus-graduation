package ru.practicum.feign.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.practicum.dto.request.requestDto.EventRequestDto;

import java.util.Optional;

@FeignClient(name = "REQUEST-SERVICE", fallback = FeignRequestClientFallback.class)
public interface FeignRequestClient {

    @GetMapping("/users/{userId}/requests/{requestId}/feign")
    Optional<EventRequestDto> getByEventIdAndRequesterId(@PathVariable Long eventId, @PathVariable Long userId);
}

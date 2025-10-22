package ru.practicum.feign.event;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.dto.event.eventDto.EventFullDto;

import java.util.Optional;

@FeignClient(name = "EVENT-SERVICE", fallback = FeignEventClientFallback.class)
public interface FeignEventClient {

    @GetMapping("/event/{eventId}/feign")
    Optional<EventFullDto> getEventById(@RequestParam Long id);

    @GetMapping("/event/{eventId}/{userId}/feign")
    Optional<EventFullDto> getByIdAndInitiator(@PathVariable Long eventId, @PathVariable Long userId);

    @PutMapping("/event/{eventId}/{requestAmount}/feign")
    Boolean updateConfirmedRequests(@PathVariable Long eventId, @PathVariable Integer requestAmount);
}

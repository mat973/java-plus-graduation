package ru.practicum.feign.request;

import ru.practicum.dto.request.requestDto.EventRequestDto;

import java.util.Optional;

public class FeignRequestLikeClientFallback implements FeingLikeRequestClient{
    @Override
    public Optional<EventRequestDto> getByEventIdAndRequesterId(Long eventId, Long userId) {
        return Optional.empty();
    }
}

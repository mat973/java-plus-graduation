package ru.practicum.service;



import ru.practicum.dto.request.requestDto.EventRequestDto;
import ru.practicum.dto.request.requestDto.EventRequestUpdateDto;
import ru.practicum.dto.request.requestDto.EventRequestUpdateResult;

import java.util.List;
import java.util.Optional;

public interface EventRequestService {
    List<EventRequestDto> getUsersRequests(Long userId);

    EventRequestDto createRequest(Long userId, Long eventId);

    EventRequestDto cancelRequest(Long userId, Long requestId);

    List<EventRequestDto> getAllByEventId(Long userId, Long eventId);

    EventRequestUpdateResult updateRequestState(Long userId, Long eventId, EventRequestUpdateDto updateDto);

    Optional<EventRequestDto> getByEventIdAndRequesterId(Long eventId, Long userId);
}

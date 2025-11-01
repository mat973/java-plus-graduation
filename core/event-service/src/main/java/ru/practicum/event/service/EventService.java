package ru.practicum.event.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.dto.event.eventDto.EventFullDto;
import ru.practicum.dto.event.eventDto.EventSearchParam;
import ru.practicum.dto.event.eventDto.EventShortDto;
import ru.practicum.dto.request.requestDto.NewEventRequest;
import ru.practicum.dto.request.requestDto.UpdateEventRequest;


import java.util.List;
import java.util.Optional;

public interface EventService {
    EventFullDto addEvent(Long userId, NewEventRequest request);

    EventFullDto updateEventByUser(Long userId, Long eventId, UpdateEventRequest request);

    EventFullDto updateEventByAdmin(Long eventId, UpdateEventRequest request);

    EventFullDto getByIdPublic(Long eventId, String ip, Long userId);

    List<EventShortDto> getUsersEvents(Long userId, Pageable page, String ip);

    EventFullDto getByIdPrivate(Long userId, Long eventId, String ip);

    List<EventShortDto> getEventsWithParamAdmin(EventSearchParam eventSearchParam, Pageable page);

    List<EventShortDto> getEventsWithParamPublic(EventSearchParam eventSearchParam, Pageable page, String ip);

    Optional<EventFullDto> getEventByIdFeign(Long id);

    Optional<EventFullDto> getEventByIdAndInitiator(Long eventId, Long userId);

    Boolean updateConfirmedRequests(Long eventId, Integer requestAmount);

    List<EventFullDto> getRecommendations(Long userId);

    void setLike(Long userId, Long eventId);
}

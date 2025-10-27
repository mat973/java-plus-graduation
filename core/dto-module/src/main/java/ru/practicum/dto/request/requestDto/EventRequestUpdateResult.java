package ru.practicum.dto.request.requestDto;

import lombok.*;

import java.util.List;

@Builder
@Data
public class EventRequestUpdateResult {
    List<EventRequestDto> confirmedRequests;
    List<EventRequestDto> rejectedRequests;
}


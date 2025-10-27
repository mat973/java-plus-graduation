package ru.practicum.dto.request.requestDto;

import lombok.*;

import java.util.List;

@Builder
@Data
public class EventRequestUpdateDto {
    List<Long> requestIds;
    String status;
}

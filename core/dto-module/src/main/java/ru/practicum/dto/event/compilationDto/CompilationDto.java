package ru.practicum.dto.event.compilationDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.dto.event.eventDto.EventShortDto;


import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CompilationDto {
    List<EventShortDto> events;
    Long id;
    Boolean pinned;
    String title;
}

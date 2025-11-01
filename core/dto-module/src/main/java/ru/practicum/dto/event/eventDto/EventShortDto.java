package ru.practicum.dto.event.eventDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import ru.practicum.dto.event.categoryDto.CategoryDto;
import ru.practicum.dto.event.locationDto.LocationDto;
import ru.practicum.dto.user.UserDto.UserShortDto;




import java.time.LocalDateTime;

@Data
@Builder
public class EventShortDto {
    Long id;
    String title;
    String annotation;
    CategoryDto category;
    Boolean paid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;

    UserShortDto initiator;
    double rating;
    int confirmedRequests;
    String description;
    int participantLimit;
    State state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdOn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime publishedOn;

    LocationDto location;
    Boolean requestModeration;

}

package ru.practicum.dto.event.eventDto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.dto.event.categoryDto.CategoryDto;
import ru.practicum.dto.event.locationDto.LocationDto;
import ru.practicum.dto.user.UserDto.UserShortDto;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventFullDto {
    String annotation;
    CategoryDto category;
    Integer confirmedRequests;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdOn;
    String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    Long id;
    UserShortDto initiator;
    LocationDto location;
    Boolean paid;
    Integer participantLimit;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime publishedOn;
    Boolean requestModeration;
    State state;
    String title;
    Double rating;
}

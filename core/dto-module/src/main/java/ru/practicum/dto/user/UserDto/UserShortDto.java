package ru.practicum.dto.user.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserShortDto {
    Long id;
    String name;
}

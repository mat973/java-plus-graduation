package ru.practicum.feign.user;

import ru.practicum.dto.user.UserDto.UserDto;

import java.util.Optional;

public class FeignUserClientFallback implements FeignUserClient{


    @Override
    public Optional<UserDto> getUserById(Long id) {
        return Optional.empty();
    }
}

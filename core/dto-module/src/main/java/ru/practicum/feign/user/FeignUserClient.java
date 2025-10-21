package ru.practicum.feign.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.practicum.dto.user.UserDto.UserDto;

import java.util.Optional;

@FeignClient(name = "USER-SERVICE", fallback = FeignUserClientFallback.class)
public interface FeignUserClient {


    @GetMapping("/admin/users/{id}")
    Optional<UserDto> getUserById(@PathVariable Long id);
}

package ru.practicum.dto.exeptions;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(Long userId) {
        super("Пользователя с id " + userId + " е существует");
    }
}

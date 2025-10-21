package ru.practicum.dto.user.exeptions;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(Long userId) {
        super("Пользователя с id " + userId + " е существует");
    }
}

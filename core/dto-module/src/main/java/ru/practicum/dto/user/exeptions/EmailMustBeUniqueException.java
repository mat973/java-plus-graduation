package ru.practicum.dto.user.exeptions;

public class EmailMustBeUniqueException extends RuntimeException {
    public EmailMustBeUniqueException(String email) {
        super("пользователь с email " + email + " уже существует");
    }
}

package ru.practicum.dto.exeptions;

public class TimeOutException extends RuntimeException {
    public TimeOutException(String message) {
        super(message);
    }
}

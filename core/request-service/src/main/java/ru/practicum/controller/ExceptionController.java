package ru.practicum.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.dto.exeptions.InvalidRequestException;
import ru.practicum.dto.exeptions.NotValidUserException;
import ru.practicum.dto.exeptions.RequestModerationException;
import ru.practicum.dto.exeptions.TimeOutException;
import ru.practicum.dto.exeptions.dto.ExceptionDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
@Slf4j
public class ExceptionController {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto invalidRequestExceptionHandler(InvalidRequestException e) {
        log.error("Обрабатываем исключение InvalidRequestException");
        return ExceptionDto.builder()
                .status(HttpStatus.BAD_REQUEST.toString())
                .reason("request is invalid")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now().format(FORMATTER))
                .build();
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionDto handleRequestModeration(final RequestModerationException e) {
        log.error("Обрабатываем исключение RequestModerationException");
        return ExceptionDto.builder()
                .status(HttpStatus.CONFLICT.toString())
                .reason("For the requested operation the conditions are not met.")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now().format(FORMATTER))
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionDto notValidUserExceptionHandler(NotValidUserException e) {
        log.error("Обрабатываем исключение NotValidUserException");
        return ExceptionDto.builder()
                .status(HttpStatus.FORBIDDEN.toString())
                .reason("Только владлец заявки может ее отменять")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now().format(FORMATTER))
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ExceptionDto notValidUserExceptionHandler(TimeOutException e) {
        log.error("Обрабатываем исключение NotValidUserException");
        return ExceptionDto.builder()
                .status(HttpStatus.SERVICE_UNAVAILABLE.toString())
                .reason("Сервис эвентов сейчас недоступен")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now().format(FORMATTER))
                .build();
    }


}

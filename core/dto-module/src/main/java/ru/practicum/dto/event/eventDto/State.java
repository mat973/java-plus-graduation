package ru.practicum.dto.event.eventDto;

public enum State {
    //ожидает публикации
    PENDING,

    //опубликовано, редактировать уже нельзя
    PUBLISHED,

    //отменено, пользователям не отображать, нельзя редактировать
    CANCELED
}

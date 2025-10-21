package ru.practicum.comment.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.comment.dto.CommentDto;
import ru.practicum.comment.dto.NewCommentDto;
import ru.practicum.comment.model.Comment;
import ru.practicum.dto.user.UserDto.UserDto;
import ru.practicum.event.model.Event;
import ru.practicum.event.model.State;


import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentMapper {

    public static Comment toEntity(NewCommentDto dto, UserDto user, Event event) {
        return Comment.builder()
                .text(dto.getText())
                .created(LocalDateTime.now())
                .creatorId(user.getId())
                .event(event)
                .state(State.PENDING)
                .build();
    }

    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .created(comment.getCreated())
                .creatorName(comment.getName())
                .eventName(comment.getEvent().getTitle())
                .state(comment.getState())
                .build();
    }
}

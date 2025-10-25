package ru.practicum.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import ru.practicum.dto.comment.commentDto.CommentDto;
import ru.practicum.dto.comment.commentDto.NewCommentDto;
import ru.practicum.dto.event.eventDto.EventFullDto;
import ru.practicum.dto.event.eventDto.State;
import ru.practicum.model.Comment;
import ru.practicum.dto.user.UserDto.UserDto;



import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentMapper {

    public static Comment toEntity(NewCommentDto dto, UserDto user, EventFullDto event) {
        return Comment.builder()
                .text(dto.getText())
                .created(LocalDateTime.now())
                .creatorId(user.getId())
                .event(event.getId())
                .title(event.getTitle())
                .state(State.PENDING)
                .build();
    }

    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .created(comment.getCreated())
                .creatorName(comment.getName())
                .eventName(comment.getTitle())
                .state(comment.getState())
                .build();
    }
}

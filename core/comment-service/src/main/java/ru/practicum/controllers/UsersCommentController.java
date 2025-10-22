package ru.practicum.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ru.practicum.dto.comment.commentDto.CommentDto;
import ru.practicum.dto.comment.commentDto.NewCommentDto;
import ru.practicum.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events")
@Slf4j
public class UsersCommentController {
    private final CommentService commentService;

    @GetMapping("/comments")
    public List<CommentDto> getUserComments(@PathVariable Long userId) {
        log.info("Получение всех комментариев пользователя id = {}", userId);
        return commentService.getCommentsByUser(userId);
    }

    @GetMapping("/comments/{eventId}")
    public List<CommentDto> getEventComments(@PathVariable Long userId,
                                             @PathVariable Long eventId) {
        log.info("Получение всех комментариев для мероприятия id = {}", eventId);
        return commentService.getCommentsByEvent(eventId);
    }

    @GetMapping("/comments/{commentId}")
    public CommentDto getById(@PathVariable Long userId,
                              @PathVariable Long commentId) {
        log.info("Получение комментария по id={}", commentId);
        return commentService.getById(commentId);
    }

    @PostMapping("/comments/{eventId}")
    public CommentDto addComment(@PathVariable Long userId,
                                 @PathVariable Long eventId,
                                 @Valid @RequestBody NewCommentDto dto) {
        log.info("Добавление комментария пользователем id={} для мероприятия id={}", userId, eventId);
        return commentService.createComment(userId, eventId, dto);
    }

    @PatchMapping("/comments/{commentId}")
    public CommentDto editComment(@PathVariable Long userId,
                                  @PathVariable Long commentId,
                                  @Valid @RequestBody NewCommentDto dto) {
        log.info("Обновление комментария id={} пользователем id={}", commentId, userId);
        return commentService.updateComment(userId, commentId, dto);
    }

    @DeleteMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long userId,
                              @PathVariable Long commentId) {
        log.info("Удаление комментария id={} пользователем id={}", commentId, userId);
        commentService.deleteComment(userId, commentId);
    }
}

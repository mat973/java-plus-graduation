package ru.practicum.feign.coment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.comment.commentDto.CommentDto;
import ru.practicum.dto.comment.commentDto.CommentSearchParam;
import ru.practicum.dto.comment.commentDto.CommentUpdateRequestDto;
import ru.practicum.feign.event.FeignEventClientFallback;

import java.util.List;

@FeignClient(name = "COMMENT-SERVICE", fallback = FeignCommentClientFallback.class)
public interface FeignCommentClient {



}

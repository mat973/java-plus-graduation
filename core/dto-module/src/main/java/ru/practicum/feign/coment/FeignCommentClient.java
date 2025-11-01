package ru.practicum.feign.coment;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "COMMENT-SERVICE", fallback = FeignCommentClientFallback.class)
public interface FeignCommentClient {


}

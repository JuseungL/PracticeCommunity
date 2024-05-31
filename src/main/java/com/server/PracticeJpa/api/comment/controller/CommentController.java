package com.server.PracticeJpa.api.comment.controller;

import com.server.PracticeJpa.api.comment.service.CommentCommandService;
import com.server.PracticeJpa.api.comment.service.CommentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

}

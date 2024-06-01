package com.server.PracticeJpa.api.comment.controller;

import com.server.PracticeJpa.api.comment.dto.request.CommentCreateRequestDto;
import com.server.PracticeJpa.api.comment.dto.response.CommentGetAllRequestDto;
import com.server.PracticeJpa.api.comment.service.CommentCommandService;
import com.server.PracticeJpa.api.comment.service.CommentQueryService;
import com.server.PracticeJpa.common.response.ApiResponse;
import com.server.PracticeJpa.common.util.MemberUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.server.PracticeJpa.common.response.SuccessStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommentController {
    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;
    @PostMapping("/contents/{contentId}/comment")
    public ResponseEntity<ApiResponse<Object>> createComment(
            Principal principal,
            @PathVariable(name = "contentId") Long contentId,
            @Valid @RequestBody CommentCreateRequestDto commentCreateRequestDto)
    {
        commentCommandService.createComment(MemberUtil.getMemberId(principal),contentId, commentCreateRequestDto);
        return ApiResponse.success(CREATE_COMMENT_SUCCESS);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<Object>> deleteComment(Principal principal, @PathVariable(name = "commentId") Long commentId) {
        commentCommandService.deleteComment(MemberUtil.getMemberId(principal), commentId);
        return ApiResponse.success(DELETE_COMMENT_SUCCESS);
    }

    // Pagination X - 쿼리 세방 나감... 더 좋은 방법 없나
    @GetMapping("/contents/{contentId}/comments")
    public ResponseEntity<ApiResponse<List<CommentGetAllRequestDto>>> getComments(@PathVariable(name = "contentId") Long contentId) {
        List<CommentGetAllRequestDto> comments = commentQueryService.getComments(contentId);
        return ApiResponse.success(GET_COMMENT_ALL_SUCCESS, comments);
    }
}

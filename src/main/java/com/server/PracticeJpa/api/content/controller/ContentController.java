package com.server.PracticeJpa.api.content.controller;

import com.server.PracticeJpa.api.content.dto.request.ContentCreateRequestDto;
import com.server.PracticeJpa.api.content.dto.request.ContentPatchReqeustDto;
import com.server.PracticeJpa.api.content.service.ContentCommandService;
import com.server.PracticeJpa.common.response.ApiResponse;
import com.server.PracticeJpa.common.response.SuccessStatus;
import com.server.PracticeJpa.common.util.MemberUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contents")
public class ContentController {
    private final ContentCommandService contentCommandService;
//    private final ContentQueryService contentQueryService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createContent(Principal principal, @Valid @RequestBody ContentCreateRequestDto contentCreateRequestDto) {
        Long memberId = MemberUtil.getMemberId(principal);
        contentCommandService.createContent(memberId, contentCreateRequestDto);
        return ApiResponse.success(SuccessStatus.CREATE_CONTENT_SUCCESS);
    }

    @PatchMapping("/{contentId}")
    public ResponseEntity<ApiResponse<Object>> updateContent(Principal principal, @PathVariable(name = "contentId") Long contentId, @Valid @RequestBody ContentPatchReqeustDto contentUpdateReqeustDto) {
        Long memberId = MemberUtil.getMemberId(principal);
        contentCommandService.updateContent(memberId, contentId, contentUpdateReqeustDto);
        return ApiResponse.success(SuccessStatus.UPDATE_CONTENT_SUCCESS);
    }

    @DeleteMapping("/{contentId}")
    public ResponseEntity<ApiResponse<Object>> deleteContent(Principal principal, @PathVariable(name = "contentId") Long contentId) {
        Long memberId = MemberUtil.getMemberId(principal);
        contentCommandService.deleteContent(memberId, contentId);
        return ApiResponse.success(SuccessStatus.DELETE_CONTENT_SUCCESS);
    }

}

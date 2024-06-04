package com.server.PracticeJpa.api.content.controller;

import com.server.PracticeJpa.api.content.domain.ContentType;
import com.server.PracticeJpa.api.content.dto.request.ContentCreateRequestDto;
import com.server.PracticeJpa.api.content.dto.request.ContentPatchReqeustDto;
import com.server.PracticeJpa.api.content.dto.response.ContentGetAllResponseDto;
import com.server.PracticeJpa.api.content.service.ContentCommandService;
import com.server.PracticeJpa.api.content.service.ContentQueryService;
import com.server.PracticeJpa.common.response.ApiResponse;
import com.server.PracticeJpa.common.response.SuccessStatus;
import com.server.PracticeJpa.common.util.MemberUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contents")
public class ContentController {
    private final ContentCommandService contentCommandService;
    private final ContentQueryService contentQueryService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createContent(Principal principal, @Valid @RequestBody ContentCreateRequestDto contentCreateRequestDto) {
        Long memberId = MemberUtil.getMemberId(principal);
        contentCommandService.createContent(memberId, contentCreateRequestDto);
        return ApiResponse.success(SuccessStatus.CREATE_CONTENT_SUCCESS);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ContentGetAllResponseDto>>> getContents(@RequestParam(required = false) String kw, ContentType contentType) {
        return ApiResponse.success(SuccessStatus.GET_CONTENT_ALL_SUCCESS, contentQueryService.getContents(kw, contentType));
    }


    @GetMapping("/{contentId}/detail")
    public ResponseEntity<ApiResponse<Object>> getContent(@PathVariable(name = "contentId") Long contentId) {
        return ApiResponse.success(SuccessStatus.GET_CONTENT_DETAIL_SUCCESS, contentQueryService.getContent(contentId));
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

package com.server.PracticeJpa.api.content.controller;

import com.server.PracticeJpa.api.content.dto.request.ContentCreateRequestDto;
import com.server.PracticeJpa.api.content.service.ContentCommandService;
import com.server.PracticeJpa.api.content.service.ContentQueryService;
import com.server.PracticeJpa.common.response.ApiResponse;
import com.server.PracticeJpa.common.response.SuccessStatus;
import com.server.PracticeJpa.common.util.MemberUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/content")
public class ContentController {
    private final ContentCommandService contentCommandService;
//    private final ContentQueryService contentQueryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> createContent(Principal principal, @Valid @RequestBody ContentCreateRequestDto contentCreateRequestDto) {
        Long memberId = MemberUtil.getMemberId(principal);
        log.debug("memberId", memberId);
        contentCommandService.createPost(memberId, contentCreateRequestDto); // 잘못된 파라미터 수정
        return ApiResponse.success(SuccessStatus.CREATE_CONTENT_SUCCESS);
    }

}

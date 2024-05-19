package com.server.InvestiMate.api.chat.controller;

import com.server.InvestiMate.api.chat.dto.request.ChatCreateRunDto;
import com.server.InvestiMate.api.chat.dto.request.ChatCreateThreadDto;
import com.server.InvestiMate.api.chat.dto.response.RunResponseDto;
import com.server.InvestiMate.api.chat.service.ChatService;
import com.server.InvestiMate.common.response.ApiResponse;
import com.server.InvestiMate.common.response.SuccessStatus;
import com.server.InvestiMate.common.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    /**
     * Thread 생성
     * 하나의 채팅방으로 보면 됨
     */
    @PostMapping("/thread")
    public ResponseEntity<ApiResponse<Object>> createThread(Principal principal, @RequestBody ChatCreateThreadDto chatSaveAssistantDto) {
        String memberOAuth2Id = MemberUtil.getMemberOAuth2Id(principal);
        chatService.createThread(memberOAuth2Id, chatSaveAssistantDto);
        return ApiResponse.success(SuccessStatus.CREATE_THREAD_SUCCESS);
    }

    /**
     * 질문 전달 후 응답 받기
     */
//    @PostMapping("/run")
//    public ResponseEntity<ApiResponse<Object>> createRun(Principal principal, @RequestBody ChatCreateRunDto chatCreateRunDto) {
//        String memberOAuth2Id = MemberUtil.getMemberOAuth2Id(principal);
//        RunResponseDto run = chatService.createRun(memberOAuth2Id, chatCreateRunDto);
//        return ApiResponse.success(SuccessStatus.CREATE_THREAD_SUCCESS);
//    }
}

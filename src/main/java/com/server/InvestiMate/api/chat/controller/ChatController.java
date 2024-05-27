package com.server.InvestiMate.api.chat.controller;

import com.server.InvestiMate.api.chat.dto.request.ChatCreateThreadDto;
import com.server.InvestiMate.api.chat.service.ChatService;
import com.server.InvestiMate.common.response.ApiResponse;
import com.server.InvestiMate.common.response.SuccessStatus;
import com.server.InvestiMate.common.util.MemberUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    public ResponseEntity<ApiResponse<Object>> createThread(Principal principal, @Valid @RequestBody ChatCreateThreadDto chatSaveAssistantDto) {
        String memberOAuth2Id = MemberUtil.getMemberOAuth2Id(principal);
        chatService.createThread(memberOAuth2Id, chatSaveAssistantDto);
        return ApiResponse.success(SuccessStatus.CREATE_THREAD_SUCCESS);
    }

}

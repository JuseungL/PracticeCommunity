package com.server.InvestiMate.common.client.openai;

import com.server.InvestiMate.api.chat.dto.request.RunRequestDto;
import com.server.InvestiMate.api.chat.dto.response.RunResponseDto;
import com.server.InvestiMate.api.chat.dto.response.ThreadsResponseDto;
import com.server.InvestiMate.common.client.openai.header.AssistantHeaderConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "OpenAiAssistantsClient", url = "https://api.openai.com/v1", configuration = AssistantHeaderConfiguration.class)
public interface AssistantsClient {

    @PostMapping("/threads")
    ThreadsResponseDto createThreads();
//
//    @PostMapping("/threads/{threadId}/messages")
//    MessagesResponseDto createMessages(@PathVariable String threadId, @RequestBody MessagesRequestDto messagesRequestDto);
//
//    @GetMapping("/threads/{threadId}/messages")
//    MessagesListResponseDto getMessagesList(@PathVariable String threadId);
//
    @PostMapping("/threads/{threadId}/runs")
    RunResponseDto createRuns(@PathVariable String threadId, @RequestBody RunRequestDto runRequestDto);

//    @GetMapping("/threads/{threadId}/runs/{runId}")
//    RunsResponseDto getRun(@PathVariable String threadId, @PathVariable String runId);

}

package com.server.InvestiMate.common.client.openai;

import com.server.InvestiMate.api.chat.dto.response.ThreadsResponseDto;
import com.server.InvestiMate.common.client.openai.header.AssistantHeaderConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "OpenAiAssistantsClient", url = "https://api.openai.com/v1", configuration = AssistantHeaderConfiguration.class)
public interface AssistantsClient {
    @PostMapping("/threads")
    ThreadsResponseDto createThreads();

}

package com.server.InvestiMate.api.chat.dto.request;

public record ChatCreateRunDto(
        String assistantId,
        String threadId,
        String instruction
) {
}

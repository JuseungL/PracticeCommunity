package com.server.InvestiMate.api.chat.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RunRequestDto(
        @JsonProperty("assistant_id")
        String assistantId,
        String instructions,
        Boolean stream
) {
    public static RunRequestDto create(String assistantId, String instructions) {
        return new RunRequestDto(assistantId, instructions, true);
    }
}

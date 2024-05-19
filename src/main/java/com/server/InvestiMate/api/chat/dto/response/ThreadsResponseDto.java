package com.server.InvestiMate.api.chat.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ThreadsResponseDto(
        String id,
        String object,
        @JsonProperty("created_at") long createdAt,
        Object metadata,
        @JsonProperty("tool_resources") Object toolResources
) {}
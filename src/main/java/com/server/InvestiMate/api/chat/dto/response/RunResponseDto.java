package com.server.InvestiMate.api.chat.dto.response;

import java.util.List;
import java.util.Map;

public record RunResponseDto(
        String id,
        String object,
        long created_at,
        String assistant_id,
        String thread_id,
        String status,
        long started_at,
        Long expires_at,
        Long cancelled_at,
        Long failed_at,
        long completed_at,
        String last_error,
        String model,
        String instructions,
        String incomplete_details,
        List<Tool> tools,
        Map<String, Object> metadata,
        String usage,
        double temperature,
        double top_p,
        int max_prompt_tokens,
        int max_completion_tokens,
        TruncationStrategy truncation_strategy,
        String response_format,
        String tool_choice
) {
    public static record Tool(String type) {} // Inner record class for Tool
    public static record TruncationStrategy(String type, List<String> last_messages) {} // Inner record class for TruncationStrategy
}

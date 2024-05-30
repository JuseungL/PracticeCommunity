package com.server.PracticeJpa.api.content.dto.request;

public record ContentPatchReqeustDto(
        Long contentId,
        String title,
        String contentText
) {
}

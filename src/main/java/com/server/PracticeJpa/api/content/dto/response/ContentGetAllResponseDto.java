package com.server.PracticeJpa.api.content.dto.response;

import com.server.PracticeJpa.api.content.domain.Content;
import com.server.PracticeJpa.api.member.domain.Member;

import java.time.LocalDateTime;

public record ContentGetAllResponseDto(
        Long contentId,
        String title,
        String contentText,
        LocalDateTime createdDate,
        String createdBy,
        Long createdById
) {
    public static ContentGetAllResponseDto of(Content content) {
        return new ContentGetAllResponseDto(
                content.getId(),
                content.getTitle(),
                content.getContentText(),
                content.getCreatedDate(),
                content.getMember().getNickname(),
                content.getMember().getId()
        );
    }
}

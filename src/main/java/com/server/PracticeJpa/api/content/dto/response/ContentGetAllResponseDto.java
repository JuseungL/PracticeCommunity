package com.server.PracticeJpa.api.content.dto.response;

import com.server.PracticeJpa.api.content.domain.Content;
import com.server.PracticeJpa.api.content.domain.ContentType;
import com.server.PracticeJpa.common.util.TimeUtil;

public record ContentGetAllResponseDto(
        Long contentId,
        ContentType contentType,
        String title,
        String contentText,
        String createdDate,
        String createdBy,
        Long createdById
) {
    public static ContentGetAllResponseDto of(Content content) {
        return new ContentGetAllResponseDto(
                content.getId(),
                content.getContentType(),
                content.getTitle(),
                content.getContentText(),
                TimeUtil.refineTime(content.getCreatedDate()),
                content.getMember().getNickname(),
                content.getMember().getId()
        );
    }
}

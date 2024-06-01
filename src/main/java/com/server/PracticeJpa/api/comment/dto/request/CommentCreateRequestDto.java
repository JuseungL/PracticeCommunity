package com.server.PracticeJpa.api.comment.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CommentCreateRequestDto(
        @NotBlank String commentText
) {
}

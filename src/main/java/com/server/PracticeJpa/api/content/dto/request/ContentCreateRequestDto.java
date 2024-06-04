package com.server.PracticeJpa.api.content.dto.request;

import com.server.PracticeJpa.api.content.domain.ContentType;
import jakarta.validation.constraints.NotBlank;

public record ContentCreateRequestDto (
        @NotBlank ContentType contentType,
        @NotBlank String title,
        @NotBlank String contentText
){

}

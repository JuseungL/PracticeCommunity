package com.server.PracticeJpa.api.content.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ContentCreateRequestDto (
        @NotBlank String title,
        @NotBlank String contentText
){

}

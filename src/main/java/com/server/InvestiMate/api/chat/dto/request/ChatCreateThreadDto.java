package com.server.InvestiMate.api.chat.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChatCreateThreadDto(
        @NotBlank String year,
        @NotBlank String companyName,
        @NotBlank String reportType
) {
}

package com.server.InvestiMate.api.chat.dto.request;

public record ChatCreateThreadDto(
        String year,
        String companyName,
        String reportType
) {
}

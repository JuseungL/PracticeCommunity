package com.server.PracticeJpa.api.member.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MemberSaveProfileDto(
    @NotBlank String nickname,
    String memberIntro
) {
}

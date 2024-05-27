package com.server.InvestiMate.api.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;

public record MemberSaveProfileDto(
    @NotBlank String nickname,
    String memberIntro
) {
}

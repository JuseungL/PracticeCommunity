package com.server.InvestiMate.api.auth.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthRequestDto (
        @NotBlank String refreshToken
){
}

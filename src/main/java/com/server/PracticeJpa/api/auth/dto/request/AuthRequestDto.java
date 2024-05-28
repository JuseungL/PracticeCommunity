package com.server.PracticeJpa.api.auth.dto.request;


import jakarta.validation.constraints.NotBlank;

public record AuthRequestDto (
        @NotBlank String refreshToken
){
}

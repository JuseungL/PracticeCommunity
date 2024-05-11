package com.server.InvestiMate.api.auth.dto.response;

public record AuthTokenResponseDto (
        String accessToken
){
    public static AuthTokenResponseDto of(String accessToken) {
        return new AuthTokenResponseDto(accessToken);
    }
}
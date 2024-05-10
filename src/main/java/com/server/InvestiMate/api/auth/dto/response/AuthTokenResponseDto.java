package com.server.InvestiMate.api.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenResponseDto {
    private String accessToken;

    private String refreshToken;
    public static AuthTokenResponseDto of (String accessToken, String refreshToken) {
        return new AuthTokenResponseDto(accessToken, refreshToken);
    }
}
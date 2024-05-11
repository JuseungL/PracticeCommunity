package com.server.InvestiMate.api.auth.controller;

import com.server.InvestiMate.api.auth.dto.request.AuthRequestDto;
import com.server.InvestiMate.api.auth.dto.response.AuthTokenResponseDto;

import com.server.InvestiMate.api.auth.service.AuthService;
import com.server.InvestiMate.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.server.InvestiMate.common.response.SuccessStatus.GET_NEW_TOKEN_SUCCESS;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReissueController {

    private final AuthService authService;
    @PostMapping("/token")
    public ResponseEntity<ApiResponse<AuthTokenResponseDto>> getNewAccessToken(@RequestBody AuthRequestDto authRequestDto) {
        AuthTokenResponseDto newToken = authService.getNewToken(authRequestDto.refreshToken());
        return ApiResponse.success(GET_NEW_TOKEN_SUCCESS, newToken);
    }
}

//package com.server.InvestiMate.api.auth.controller;
//
//import com.server.InvestiMate.api.auth.dto.response.AuthTokenResponseDto;
//
//import com.server.InvestiMate.common.response.ApiResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import static com.server.InvestiMate.common.response.SuccessStatus.GET_NEW_TOKEN_SUCCESS;
//
//@Slf4j
//@RestController
//@RequestMapping("/api/v1/auth")
//@RequiredArgsConstructor
//public class ReissueController {
//
////    private AuthService authService;
//    @PostMapping("/token")
//    @ResponseStatus(HttpStatus.OK)
////    @Operation(summary = "토큰 재발급 API",description = "token")
//    public ResponseEntity<ApiResponse<AuthTokenResponseDto>> getNewAccessToken(@RequestHeader("Authorization") String authHeader, String refreshToken) {
//        return ApiResponse.success(GET_NEW_TOKEN_SUCCESS, aauthService.getNewToken(authHeader, refreshToken));
//    }
//}

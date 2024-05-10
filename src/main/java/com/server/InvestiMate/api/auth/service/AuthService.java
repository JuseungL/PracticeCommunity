//package com.server.InvestiMate.api.auth.service;
//
//import com.server.InvestiMate.api.auth.dto.response.AuthTokenResponseDto;
//import com.server.InvestiMate.common.config.jwt.JwtUtil;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class AuthService {
//    private final JwtUtil jwtUtil;
//    public AuthTokenResponseDto getNewToken(String accessToken, String refreshToken) {
//        Map<String, Object> accessClaims = jwtUtil.extractJwtClaims(accessToken);
//        String accessCategory = (String) accessClaims.get("category");
//        String oAuth2Id = (String) accessClaims.get("oAuth2Id");
//        String role = (String) accessClaims.get("role");
//        jwtUtil.generateAccessToken(accessCategory,oAuth2Id,role, JwtConstants.ACCESS_EXP_TIME);
//
//        Map<String, Object> refreshClaims = jwtUtil.extractJwtClaims(refreshToken);
//        return AuthTokenResponseDto.of(accessToken,refreshToken);
//    }
//
//}

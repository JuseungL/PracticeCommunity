package com.server.PracticeJpa.api.auth.jwt;

import com.server.PracticeJpa.api.auth.domain.CustomOAuth2User;
import com.server.PracticeJpa.api.member.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final MemberService memberService;
    private final JwtUtil jwtUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("--------------------------- OAuth2LoginSuccessHandler ---------------------------");
        CustomOAuth2User principal = (CustomOAuth2User) authentication.getPrincipal();

        String oAuth2Id = principal.getOAuth2Id();
        String authorities = principal.getAuthorities().toString();
        String accessToken = jwtUtil.generateToken("access", oAuth2Id, authorities, jwtUtil.accessTokenExpireLength);
        String refreshToken = jwtUtil.generateToken("refresh", oAuth2Id, authorities, jwtUtil.refreshTokenExpireLength);

        response.addCookie(createCookie("access", accessToken));
        response.addCookie(createCookie("refresh", refreshToken));

        memberService.updateRefreshToken(oAuth2Id, refreshToken);
        response.sendRedirect(jwtUtil.JWT_REDIRECT);
    }
    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true); // JS가 가져 가지 못하게(XSS 방지)

        return cookie;
    }
}
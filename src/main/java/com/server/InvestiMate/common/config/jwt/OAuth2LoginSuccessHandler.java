package com.server.InvestiMate.common.config.jwt;

import com.nimbusds.jose.shaded.gson.Gson;
import com.server.InvestiMate.api.auth.domain.CustomOAuth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("--------------------------- OAuth2LoginSuccessHandler ---------------------------");

        CustomOAuth2User principal = (CustomOAuth2User) authentication.getPrincipal();

        log.info("authentication.getPrincipal() = {}", principal);

        String oAuth2Id = principal.getOAuth2Id();
        String authorities = principal.getAuthorities().toString();
        String accessToken = jwtUtil.generateJwt("access", oAuth2Id, authorities, (long) JwtConstants.ACCESS_EXP_TIME);
        String refreshToken = jwtUtil.generateJwt("refresh", oAuth2Id, authorities, (long) JwtConstants.REFRESH_EXP_TIME);

//        response.setHeader("access", accessToken);
        response.addCookie(createCookie("access", accessToken));
        response.sendRedirect("http://localhost:3000/");
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
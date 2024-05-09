package com.server.InvestiMate.common.config;

import com.server.InvestiMate.api.auth.service.CustomOAuth2UserService;
import com.server.InvestiMate.common.config.jwt.JwtAuthenticationFilter;
import com.server.InvestiMate.common.config.jwt.JwtUtil;
import com.server.InvestiMate.common.config.jwt.OAuth2LoginSuccessHandler;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 *  Spring Security 설정
 *  - CORS
 *  - OAuth2.0 + JWT
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtUtil jwtUtil;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(corsCustomizer -> corsCustomizer.configurationSource(corsConfigurationSource()));
        http
                .csrf((csrf) -> csrf.disable());
        http
                .formLogin((login) -> login.disable());

        http
                .httpBasic((basic) -> basic.disable());
        //JWTFilter 추가
        http
                .addFilterAfter(new JwtAuthenticationFilter(jwtUtil), OAuth2LoginAuthenticationFilter.class);
        http
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                        .userService(customOAuth2UserService))
                        .successHandler(oAuth2LoginSuccessHandler)
                );
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated());

        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }

    // CORS 설정 메소드
    private CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
            configuration.setAllowedMethods(Collections.singletonList("*"));
            configuration.setAllowCredentials(true);
            configuration.setAllowedHeaders(Collections.singletonList("*"));
            configuration.setMaxAge(3600L);
            configuration.setExposedHeaders(Arrays.asList("Set-Cookie", "Authorization"));
            return configuration;
        };
    }
}

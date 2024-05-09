package com.server.InvestiMate.common.config.jwt;

import com.server.InvestiMate.api.auth.domain.CustomOAuth2User;
import com.server.InvestiMate.api.member.domain.Member;
import com.server.InvestiMate.api.member.domain.RoleType;
import com.server.InvestiMate.common.config.jwt.exception.CustomExpiredJwtException;
import com.server.InvestiMate.common.config.jwt.exception.CustomJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import static com.server.InvestiMate.common.config.jwt.JwtConstants.secret;

@Component
public class JwtUtil {

    private SecretKey secretKey;

    public JwtUtil(@Value("${spring.jwt.secret}")String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public static String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }

    public static Authentication getAuthentication(String token) {
        Map<String, Object> claims = validateToken(token);

        String oAuth2Id = (String) claims.get("oAuth2Id");
        String name = (String) claims.get("name");
        String role = (String) claims.get("role");
        RoleType memberRole = RoleType.valueOf(role);

        Member jwtDto = Member.builder()
                .oAuth2Id(oAuth2Id)
                .name(name)
                .roleType(memberRole)
                .build();

        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(jwtDto.getRoleType().getKey()));
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(jwtDto);
        return new UsernamePasswordAuthenticationToken(customOAuth2User, null, authorities);
    }

    public String getCategory(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
    }

    public String getOAuth2Id(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("oAuth2Id", String.class);
    }

    public String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public static Map<String, Object> validateToken(String token) {
        Map<String, Object> claim = null;
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            claim = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token) // 파싱 및 검증, 실패 시 에러
                    .getBody();
        } catch(ExpiredJwtException expiredJwtException){
            throw new CustomExpiredJwtException("토큰이 만료되었습니다", expiredJwtException);
        } catch(Exception e){
            throw new CustomJwtException("Error");
        }
        return claim;
    }

    // 토큰이 만료되었는지 판단하는 메서드
    public static boolean isExpired(String token) {
        try {
            validateToken(token);
        } catch (Exception e) {
            return (e instanceof CustomExpiredJwtException);
        }
        return false;
    }

    public String generateJwt(String category, String oAuth2Id, String role, Long expiredMs) {

        return Jwts.builder()
                .claim("category", category)
                .claim("oAuth2Id", oAuth2Id)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }
}

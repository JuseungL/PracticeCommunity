package com.server.PracticeJpa.api.member.dto.response;

import com.server.PracticeJpa.api.member.domain.Member;
import com.server.PracticeJpa.api.member.domain.RoleType;

import java.time.LocalDateTime;

public record MemberGetProfileResponseDto(
        Long memberId,
        String oAuth2Id,
        String name,
        String Email,
        RoleType roleType,
        LocalDateTime createdDate
) {
    public static MemberGetProfileResponseDto of(Member member){
        return new MemberGetProfileResponseDto(
                member.getId(),
                member.getOAuth2Id(),
                member.getName(),
                member.getEmail(),
                member.getRoleType(),
                member.getCreatedDate()
        );
    }
}

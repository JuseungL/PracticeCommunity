package com.server.InvestiMate.api.member.dto.response;

import com.server.InvestiMate.api.member.domain.Member;
import com.server.InvestiMate.api.member.domain.RoleType;

public record MemberGetProfileResponseDto(
        Long memberId,
        String oAuth2Id,
        String name,
        String Email,
        RoleType roleType
) {
    public static MemberGetProfileResponseDto of(Member member){
        return new MemberGetProfileResponseDto(
                member.getId(),
                member.getOAuth2Id(),
                member.getName(),
                member.getEmail(),
                member.getRoleType()
        );
    }
}

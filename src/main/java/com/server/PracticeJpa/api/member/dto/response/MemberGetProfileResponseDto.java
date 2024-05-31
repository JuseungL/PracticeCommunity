package com.server.PracticeJpa.api.member.dto.response;

import com.server.PracticeJpa.api.member.domain.Member;
import com.server.PracticeJpa.api.member.domain.RoleType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberGetProfileResponseDto {
    Long memberId;
    String oAuth2Id;
    String name;
    String email;
    RoleType roleType;
    LocalDateTime createdDate;
}


package com.server.InvestiMate.api.member.service;

import com.server.InvestiMate.api.member.domain.Member;
import com.server.InvestiMate.api.member.dto.response.MemberGetProfileResponseDto;
import com.server.InvestiMate.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberGetProfileResponseDto getMemberProfile(Long memberId) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        return MemberGetProfileResponseDto.of(member);
    }
}

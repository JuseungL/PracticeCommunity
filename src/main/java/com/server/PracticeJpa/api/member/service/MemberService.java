package com.server.PracticeJpa.api.member.service;

import com.server.PracticeJpa.api.member.domain.Member;
import com.server.PracticeJpa.api.member.dto.request.MemberSaveProfileDto;
import com.server.PracticeJpa.api.member.dto.response.MemberGetProfileResponseDto;
import com.server.PracticeJpa.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public MemberGetProfileResponseDto getMemberProfile(Long memberId) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
//        return MemberGetProfileResponseDto.of(member);
        return modelMapper.map(member, MemberGetProfileResponseDto.class);
    }

    public void updateRefreshToken(Long memberId, String refreshToken) {
        Member member = memberRepository.findMemberById(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        member.updateRefreshToken(refreshToken);
        memberRepository.save(member);
    }

    public void saveMemberProfile(Long memberId, MemberSaveProfileDto memberSaveProfileDto) {
        Member byoAuth2Id = memberRepository.findMemberByIdOrThrow(memberId);
        String nickname = memberSaveProfileDto.nickname();
        String memberIntro = memberSaveProfileDto.memberIntro();
        byoAuth2Id.updateNickname(nickname);
        byoAuth2Id.updateMemberIntro(memberIntro);
    }
}

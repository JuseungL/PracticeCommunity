package com.server.PracticeJpa.api.member.service;

import com.server.PracticeJpa.api.comment.domain.Comment;
import com.server.PracticeJpa.api.comment.repository.CommentRepository;
import com.server.PracticeJpa.api.comment.service.CommentCommandService;
import com.server.PracticeJpa.api.content.domain.Content;
import com.server.PracticeJpa.api.content.repository.ContentRepository;
import com.server.PracticeJpa.api.member.domain.Member;
import com.server.PracticeJpa.api.member.domain.RoleType;
import com.server.PracticeJpa.api.member.dto.request.MemberSaveProfileDto;
import com.server.PracticeJpa.api.member.dto.response.MemberGetProfileResponseDto;
import com.server.PracticeJpa.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final ContentRepository contentRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public MemberGetProfileResponseDto getMemberProfile(Long memberId) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
//        return MemberGetProfileResponseDto.of(member);
        return modelMapper.map(member, MemberGetProfileResponseDto.class);
    }
    public List<MemberGetProfileResponseDto> getMemberProfiles() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(member -> modelMapper.map(member, MemberGetProfileResponseDto.class)).collect(Collectors.toList());
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
        byoAuth2Id.updateMemberProfile(nickname, memberIntro);
    }

    public void deleteMember(Long memberId) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        memberRepository.delete(member);
    }

    public void softDeleteMemberV1(Long memberId) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        List<Comment> comments = member.getComments();
        comments.forEach(Comment::commentSoftDelete);
        memberRepository.delete(member);
    }

    public void softDeleteMemberV2(Long memberId) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        List<Long> commentIds = member.getComments().stream()
                                        .map(Comment::getId)
                                        .collect(Collectors.toList());

        List<Long> contentIds = member.getContents().stream()
                                        .map(Content::getId)
                                        .collect(Collectors.toList());

        contentRepository.softDeleteContents(contentIds);
        commentRepository.softDeleteComments(commentIds);
        memberRepository.delete(member);
    }
}


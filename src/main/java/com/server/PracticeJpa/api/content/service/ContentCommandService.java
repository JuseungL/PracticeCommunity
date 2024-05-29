package com.server.PracticeJpa.api.content.service;

import com.server.PracticeJpa.api.content.domain.Content;
import com.server.PracticeJpa.api.content.dto.request.ContentCreateRequestDto;
import com.server.PracticeJpa.api.content.repository.ContentCommandRepository;
import com.server.PracticeJpa.api.member.domain.Member;
import com.server.PracticeJpa.api.member.repository.MemberRepository;
import com.server.PracticeJpa.api.member.service.MemberService;
import com.server.PracticeJpa.common.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentCommandService {
    private final MemberRepository memberRepository;
    private final ContentCommandRepository contentCommandRepository;
    /**
     * Create
     */
    public void createPost(Long memberId, ContentCreateRequestDto contentCreateRequestDto) {
        String title = contentCreateRequestDto.title();
        String text = contentCreateRequestDto.contentText();
        Member member = memberRepository.findMemberByIdOrThrow(memberId);

        Content content = Content.builder()
                .title(title)
                .contentText(text)
                .member(member)
                .build();

        contentCommandRepository.save(content);
    }

    /**
     * Update
     */

    /**
     * Delete
     */

}

package com.server.PracticeJpa.api.content.service;

import com.server.PracticeJpa.api.content.domain.Content;
import com.server.PracticeJpa.api.content.dto.request.ContentCreateRequestDto;
import com.server.PracticeJpa.api.content.dto.request.ContentPatchReqeustDto;
import com.server.PracticeJpa.api.content.repository.ContentRepository;
import com.server.PracticeJpa.api.member.domain.Member;
import com.server.PracticeJpa.api.member.repository.MemberRepository;
import com.server.PracticeJpa.common.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentCommandService {
    private final MemberRepository memberRepository;
    private final ContentRepository contentCommandRepository;
    /**
     * Create
     */
    public void createContent(Long memberId, ContentCreateRequestDto contentCreateRequestDto) {
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
    public void updateContent(Long memberId, Long contentId, ContentPatchReqeustDto contentPatchReqeustDto) {
        Member memberByIdOrThrow = memberRepository.findMemberByIdOrThrow(memberId);
        System.out.println("memberByIdOrThrow.toString() = " + memberByIdOrThrow.toString());
        Content existingContent = writerValidate(memberId, contentId);

        
        if (contentPatchReqeustDto.title() != null) {
            existingContent.updateTitle(contentPatchReqeustDto.title());
        }
        if (contentPatchReqeustDto.contentText() != null) {
            existingContent.updateContentText(contentPatchReqeustDto.contentText());
        }
    }

    /**
     * Delete
     */
    public void deleteContent(Long memberId, Long contentId) {
        Content content = writerValidate(memberId, contentId);
        contentCommandRepository.delete(content);
    }



    public Content writerValidate(Long memberId, Long contentId) {
        Content content = contentCommandRepository.findContentByIdOrThrow(contentId);

        Long contentMemberId = content.getMember().getId();
        if (!contentMemberId.equals(memberId)) {
            System.out.println("contentMemberId + memberId = " + contentMemberId + memberId);
            throw new IllegalArgumentException(ErrorStatus.UNAUTHORIZED_MEMBER.getMessage());
        }
        return content;
    }
}

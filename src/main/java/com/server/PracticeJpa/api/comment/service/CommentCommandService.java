package com.server.PracticeJpa.api.comment.service;

import com.server.PracticeJpa.api.comment.domain.Comment;
import com.server.PracticeJpa.api.comment.dto.request.CommentCreateRequestDto;
import com.server.PracticeJpa.api.comment.repository.CommentRepository;
import com.server.PracticeJpa.api.content.domain.Content;
import com.server.PracticeJpa.api.content.repository.ContentRepository;
import com.server.PracticeJpa.api.member.domain.Member;
import com.server.PracticeJpa.api.member.repository.MemberRepository;
import com.server.PracticeJpa.common.exception.BadRequestException;
import com.server.PracticeJpa.common.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentCommandService {
    private final MemberRepository memberRepository;
    private final ContentRepository contentRepository;
    private final CommentRepository commentRepository;

    /**
     * Create
     */
    public void createComment(Long memberId, Long contentId, CommentCreateRequestDto commentCreateRequestDto) {
        Content content = contentRepository.findContentByIdOrThrow(contentId);
        Member commetWriter = memberRepository.findMemberByIdOrThrow(memberId);

        Comment comment = Comment.builder()
                .member(commetWriter)
                .content(content)
                .commentText(commentCreateRequestDto.commentText())
                .build();
        commentRepository.save(comment);
    }

    /**
     * Delete
     */
    public void deleteComment(Long memberId, Long commentId) {
        Comment comment = deleteValidate(memberId, commentId);
        commentRepository.delete(comment);
    }
    public Comment deleteValidate(Long memberId, Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException(ErrorStatus.NOT_FOUND_COMMENT.getMessage()));

        Long commentMemberId = comment.getMember().getId();

        if (!commentMemberId.equals(memberId)) {    //답글작성자 != 현재 유저 >> 권한error
            throw new BadRequestException(ErrorStatus.UNAUTHORIZED_MEMBER.getMessage());
        }
        return comment;
    }


}

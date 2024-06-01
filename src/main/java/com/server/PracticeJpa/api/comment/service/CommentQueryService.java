package com.server.PracticeJpa.api.comment.service;

import com.server.PracticeJpa.api.comment.domain.Comment;
import com.server.PracticeJpa.api.comment.dto.response.CommentGetAllRequestDto;
import com.server.PracticeJpa.api.comment.repository.CommentRepository;
import com.server.PracticeJpa.api.content.domain.Content;
import com.server.PracticeJpa.api.content.repository.ContentRepository;
import com.server.PracticeJpa.api.member.repository.MemberRepository;
import com.server.PracticeJpa.common.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentQueryService {
    private final MemberRepository memberRepository;
    private final ContentRepository contentRepository;
    private final CommentRepository commentRepository;
    public List<CommentGetAllRequestDto> getComments(Long contentId) {
        contentRepository.findContentByIdOrThrow(contentId);
        List<Comment> comments = commentRepository.findCommentsByContentIdOrderByCreatedDateAsc(contentId);

        return comments.stream()
                .map(comment -> CommentGetAllRequestDto.of(comment.getMember(), comment))
                .collect(Collectors.toList());

    }

}

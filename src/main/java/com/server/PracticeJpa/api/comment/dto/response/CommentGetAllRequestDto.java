package com.server.PracticeJpa.api.comment.dto.response;

import com.server.PracticeJpa.api.comment.domain.Comment;
import com.server.PracticeJpa.api.member.domain.Member;
import com.server.PracticeJpa.common.util.TimeUtil;

public record CommentGetAllRequestDto(
        Long commentId,
        Long memberId,
        String memberNickname,
        String commentText,
        String time
) {
    public static CommentGetAllRequestDto of(Member commentWriter, Comment comment) {
        return new CommentGetAllRequestDto(
                comment.getId(),
                commentWriter.getId(),
                commentWriter.getNickname(),
                comment.getCommentText(),
                TimeUtil.refineTime(comment.getCreatedDate())
        );
    }
}

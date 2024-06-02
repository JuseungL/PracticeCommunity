package com.server.PracticeJpa.api.comment.repository;

import com.server.PracticeJpa.api.comment.domain.Comment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentsByContentIdOrderByCreatedDateAsc(Long contentId);
    @Query("SELECT c FROM Comment c WHERE c.id > ?1 AND c.content.id = ?2 ORDER BY c.createdDate")
    Slice<Comment> findCommentsByContentNextPage(Long lastCommentId, Long contentId, PageRequest pageRequest);

    @Modifying
    @Query("UPDATE Comment c SET c.isDeleted = true WHERE c.isDeleted = false AND c.id IN :commentIds")
    void softDeleteComments(List<Long> commentIds);
}

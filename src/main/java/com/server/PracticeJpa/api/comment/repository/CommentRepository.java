package com.server.PracticeJpa.api.comment.repository;

import com.server.PracticeJpa.api.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentsByContentIdOrderByCreatedDateAsc(Long contentId);
}

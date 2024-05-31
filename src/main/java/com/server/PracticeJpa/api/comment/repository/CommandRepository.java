package com.server.PracticeJpa.api.comment.repository;

import com.server.PracticeJpa.api.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandRepository extends JpaRepository<Comment,Long> {
}

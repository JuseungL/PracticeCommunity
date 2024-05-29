package com.server.PracticeJpa.api.content.repository;


import com.server.PracticeJpa.api.content.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentCommandRepository extends JpaRepository<Content, Long> {
}

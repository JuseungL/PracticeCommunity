package com.server.PracticeJpa.api.content.repository;


import com.server.PracticeJpa.api.content.domain.Content;
import com.server.PracticeJpa.common.exception.NotFoundException;
import com.server.PracticeJpa.common.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Long> {
    Optional<Content> findContentById(Long contentId);
    default Content findContentByIdOrThrow(Long contentId) {
        return findContentById(contentId)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_CONTENT.getMessage()));
    }

    List<Content> findAllByOrderByCreatedDateDesc();

    @Modifying
    @Query("UPDATE Content c SET c.isDeleted = true WHERE c.isDeleted = false AND c.id IN :contentIds")
    void softDeleteContents(List<Long> contentIds);
}

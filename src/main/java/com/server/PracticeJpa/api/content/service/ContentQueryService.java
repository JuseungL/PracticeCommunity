package com.server.PracticeJpa.api.content.service;

import com.server.PracticeJpa.api.content.domain.Content;
import com.server.PracticeJpa.api.content.domain.ContentType;
import com.server.PracticeJpa.api.content.dto.response.ContentGetAllResponseDto;
import com.server.PracticeJpa.api.content.repository.ContentQueryRepository;
import com.server.PracticeJpa.api.content.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentQueryService {
    private final ContentRepository contentRepository;
    private final ContentQueryRepository contentQueryRepository;
    /**
     * Read
     */
    public List<ContentGetAllResponseDto> getContents(String kw, ContentType contentType) {
//        List<Content> contents = contentRepository.findAllByOrderByCreatedDateDesc();
        List<Content> contents = contentQueryRepository.findAllBySearchAndFilterWithDsl(kw, contentType);
        return contents.stream()
                .map(content -> ContentGetAllResponseDto.of(content))
                .collect(Collectors.toList());
    }

    public ContentGetAllResponseDto getContent(Long contentId) {
        Content content = contentRepository.findContentByIdOrThrow(contentId);
        return ContentGetAllResponseDto.of(content);
    }
}

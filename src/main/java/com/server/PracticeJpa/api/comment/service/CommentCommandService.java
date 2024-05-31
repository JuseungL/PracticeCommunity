package com.server.PracticeJpa.api.comment.service;

import com.server.PracticeJpa.api.comment.repository.CommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentCommandService {
    private final CommandRepository commandRepository;
}

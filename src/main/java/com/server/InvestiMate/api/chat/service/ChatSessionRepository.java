package com.server.InvestiMate.api.chat.service;

import com.server.InvestiMate.api.chat.domain.ChatMessage;
import com.server.InvestiMate.api.chat.domain.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatSessionRepository extends JpaRepository<ChatSession, Long> {
}

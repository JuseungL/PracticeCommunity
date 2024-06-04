package com.server.PracticeJpa.api.content.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContentType {
    NORMAL("TYPE_NORMAL"), QUESTION("TYPE_QUESTION"), INFO("TYPE_INFO");
    private final String key;
}

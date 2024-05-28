package com.server.PracticeJpa.common.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

/**
 *  애플리케이션의 기본 시간대를 Asia/Seoul로 설정
 */
@Configuration
public class TimeZoneConfig {
    @PostConstruct // 해당 메소드가 빈 등록이 완료된 직후에 실행
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}

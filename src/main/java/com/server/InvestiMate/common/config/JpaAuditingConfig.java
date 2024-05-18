package com.server.InvestiMate.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 *  엔티티의 생성일자 및 수정일자를 자동으로 추적하는 Auditing 기능 활성화
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            String currentPrincipalName = SecurityContextHolder.getContext().getAuthentication().getName();
            return Optional.of(currentPrincipalName);
        };
    }
}

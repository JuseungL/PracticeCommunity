package com.server.InvestiMate.common.auditing;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 *  엔티티의 생성일자 및 수정일자를 자동으로 추적하는 Auditing 기능 활성화
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}

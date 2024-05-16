package com.server.InvestiMate.api.chat.service;

import com.server.InvestiMate.api.chat.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}

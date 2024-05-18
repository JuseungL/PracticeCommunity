package com.server.InvestiMate.api.chat.repository;

import com.server.InvestiMate.api.chat.domain.Report;
import com.server.InvestiMate.api.member.domain.Member;
import com.server.InvestiMate.common.exception.NotFoundException;
import com.server.InvestiMate.common.response.ErrorStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByReportYearAndReportCompanyAndReportType(Integer year, String companyName, String reportType);
    default Report findByReportYearAndReportCompanyAndReportTypeOrThrow(Integer year, String companyName, String reportType) {
        return findByReportYearAndReportCompanyAndReportType(year, companyName, reportType)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_REPORT.getMessage()));
    }

}

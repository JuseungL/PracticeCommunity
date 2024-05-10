package com.server.InvestiMate.api.member.repository;


import com.server.InvestiMate.api.member.domain.Member;
import com.server.InvestiMate.common.exception.NotFoundException;
import com.server.InvestiMate.common.response.ErrorStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByoAuth2Id(String oAuth2Id);

    Optional<Member> findMemberById(Long memberId);
    default Member findMemberByIdOrThrow(Long memberId) {
        return findMemberById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_MEMBER.getMessage()));
    }
}

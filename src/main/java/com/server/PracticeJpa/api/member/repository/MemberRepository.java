package com.server.PracticeJpa.api.member.repository;


import com.server.PracticeJpa.api.member.domain.Member;
import com.server.PracticeJpa.common.exception.NotFoundException;
import com.server.PracticeJpa.common.response.ErrorStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberById(Long memberId);
    default Member findMemberByIdOrThrow(Long memberId) {
        return findMemberById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_MEMBER.getMessage()));
    }

    Optional<Member> findByoAuth2Id(String oAuth2Id);
    default Member findByoAuth2IdOrThrow(String oAuth2Id) {
        return findByoAuth2Id(oAuth2Id)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_MEMBER.getMessage()));
    }

    Optional<Member> findMemberByRefreshToken(String refreshToken);
    default Member findByRefreshTokenOrThrow(String refreshToken) {
        return findMemberByRefreshToken(refreshToken)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_MEMBER.getMessage()));
    }

}

package com.server.InvestiMate.api.member.repository;


import com.server.InvestiMate.api.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByoAuth2Id(String oAuth2Id);
}

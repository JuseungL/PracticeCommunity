package com.server.PracticeJpa.api.content.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.PracticeJpa.api.content.domain.Content;
import com.server.PracticeJpa.api.content.domain.ContentType;
import com.server.PracticeJpa.api.content.domain.QContent;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.server.PracticeJpa.api.content.domain.QContent.content;
import static com.server.PracticeJpa.api.member.domain.QMember.member;

/**
 *  Query DSL을 위한 Repository임.
 */
@Repository
@RequiredArgsConstructor
public class ContentQueryRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * 이거 작성자 수만큼 쿼리됨. 근데 이때 작성자 수가 개 많아지면 그 수만큼 N+1문제가 발생할 수 있음.
     * 따라서 fetch join 처리가 필요할 것으로 보인다.
     * */
    public List<Content> findAllBySearchAndFilterWithDsl(String kw, String contentType) {
        QContent content = QContent.content;
        return queryFactory.selectFrom(content)
                // Content 엔티티와 Member 엔티티 사이의 연관 관계를 기반으로 왼쪽 조인(LEFT JOIN)을 수행
                .leftJoin(content.member, member).fetchJoin()
                .where(
                        eqKeyword(kw),
                        eqContentType(contentType)
                )
                .orderBy(content.createdDate.desc())
                .fetch();
    };


    private BooleanExpression eqKeyword(String kw) {
        System.out.println("kw = " + kw);
        if (StringUtils.isEmpty(kw)) { return null; }
        return content.title.containsIgnoreCase(kw);
    }

    private BooleanExpression eqContentType(String contentType) {
        if (StringUtils.isEmpty(contentType)) {
            return null;
        }
        ContentType ct = ContentType.valueOf(contentType);
        return content.contentType.eq(ct);
    }
}

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

/**
 *  Query DSL을 위한 Repository임.
 */
@Repository
@RequiredArgsConstructor
public class ContentQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Content> findAllBySearchAndFilterWithDsl(String kw, String contentType) {
        QContent content = QContent.content;
        return queryFactory.selectFrom(content)
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

package com.server.PracticeJpa.api.comment.domain;

import com.server.PracticeJpa.api.content.domain.Content;
import com.server.PracticeJpa.api.member.domain.Member;
import com.server.PracticeJpa.common.auditing.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@SQLDelete(sql = "UPDATE comment SET is_deleted = true, deleted_at = NOW() WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Comment extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    private String commentText;

    /**
     * Soft Delete
     */
    @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted = false;

    @Column(name = "deleted_at")
    private LocalDateTime deleteAt;

    @Builder
    public Comment(Member member, Content content, String commentText) {
        this.member = member;
        this.content = content;
        this.commentText = commentText;
    }

    public void commentSoftDelete() {
        this.isDeleted = true;
    }
}

package com.server.PracticeJpa.api.content.domain;

import com.server.PracticeJpa.api.comment.domain.Comment;
import com.server.PracticeJpa.api.member.domain.Member;
import com.server.PracticeJpa.common.auditing.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction("is_deleted = false")
public class Content extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ContentType contentType = ContentType.NORMAL;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contentText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "content", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    /**
     * Soft Delete
     */
    @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted = false;

    @Column(name = "deleted_at")
    private LocalDateTime deleteAt;

    @Builder
    public Content(ContentType contentType, String title, String contentText, Member member) {
        this.contentType = contentType;
        this.title = title;
        this.contentText = contentText;
        this.member = member;
    }

    public void updateTitle(String title) { this.title = title;}
    public void updateContentText(String contentText) {this.contentText = contentText;}

    public void addComment(Comment comment) {this.comments.add(comment);}
}

package com.server.PracticeJpa.api.content.domain;

import com.server.PracticeJpa.api.member.domain.Member;
import com.server.PracticeJpa.common.auditing.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String contentText;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Content(String title, String contentText, Member member) {
        this.title = title;
        this.contentText = contentText;
        this.member = member;
    }

    public void updateTitle(String title) { this.title = title;}
    public void updateContentText(String contentText) {this.contentText = contentText;}


}

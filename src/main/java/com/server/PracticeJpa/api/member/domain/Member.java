package com.server.PracticeJpa.api.member.domain;

import com.server.PracticeJpa.api.comment.domain.Comment;
import com.server.PracticeJpa.api.content.domain.Content;
import com.server.PracticeJpa.common.auditing.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE member SET is_deleted = true, deleted_at = NOW() WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // oauth2_id = provider + " " + provider's id
    @Column(nullable = false, name = "oauth2_id")
    private String oAuth2Id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(name = "refresh_token", length = 500) // refresh_token 속성의 최대 길이를 300으로 지정
    private String refreshToken;

    @Column(nullable = true, unique = true)
    private String nickname;

    @Column(nullable = true, name = "member_intro")
    private String memberIntro;

    @OneToMany(mappedBy = "member")
    private List<Content> contents = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    /**
     * Soft Delete
     */
    @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted = false;

    @Column(name = "deleted_at")
    private LocalDateTime deleteAt;

    @Builder
    private Member(String oAuth2Id, String name, String email, RoleType roleType){
        this.oAuth2Id = oAuth2Id;
        this.name = name;
        this.email = email;
        this.roleType = roleType;
    }
    public void updateRefreshToken(String refreshToken) { this.refreshToken = refreshToken;}

    /**
     * 각각 두지 않고 묶어서 코드 응집성을 높히고 이름도 도메인을 잘 드러내도록
     * 기존엔 updateNickname, updateMemberIntro 이렇게 분리해둠
     */
    public void updateMemberProfile(String nickname, String memberIntro) {
        this.nickname = nickname;
        this.memberIntro = memberIntro;
    }
}

package com.server.InvestiMate.api.member.domain;

import com.server.InvestiMate.common.auditing.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

//    @Column(nullable = true)
//    private String nickname;


    @Builder
    private Member(String oAuth2Id, String name, String email, RoleType roleType){
        this.oAuth2Id = oAuth2Id;
        this.name = name;
        this.email = email;
        this.roleType = roleType;
    }

    // refreshToken 업데이트 메소드
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

//    public void updateNickname(String newNickname) {
//        this.nickname = newNickname;
//    }

}

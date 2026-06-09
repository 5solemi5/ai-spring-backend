package com.sesac.aibackend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 로그인 아이디 (유일)
     */
    @Column(
            unique = true,
            nullable = false,
            length = 100
    )
    private String username;

    /**
     * BCrypt 해시 (Day4에서 사용)
     */
    @Column(length = 200)
    private String passwordHash;

    /**
     * USER / ADMIN
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    /** 인증 출처: "LOCAL"(폼 가입) 또는 "GOOGLE"(소셜 로그인). */
    @Builder.Default
    @Column(nullable = false, length = 20)
    private String provider = "LOCAL";

    /** 소셜 공급자의 안정적 고유 식별자(OIDC sub). LOCAL 사용자는 NULL. */
    @Column(name = "provider_id", length = 255)
    private String providerId;

    public static User oauthUser(String email, String providerId) {
        return User.builder()
                .username(email)
                .passwordHash(null)
                .role(Role.USER)
                .provider("GOOGLE")
                .providerId(providerId)
                .build();
    }
    /**
     * 역할 변경 도메인 메서드.
     *
     * Lombok @Setter를 두지 않고 의도된 메서드만 노출해 캡슐화를 유지합니다.
     * 값 검증은 입력 경계에서 enum 타입(RoleUpdateRequest.role)으로 강제됩니다.
     */
    public void changeRole(Role role) {
        this.role = role;
    }
}
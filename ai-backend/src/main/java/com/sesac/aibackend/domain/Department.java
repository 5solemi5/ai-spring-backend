package com.sesac.aibackend.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Builder
    private Department(String name) {
        this.name = name;
    }

    public void update(String name) {
        this.name = name;
    }
}
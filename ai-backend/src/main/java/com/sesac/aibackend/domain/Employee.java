package com.sesac.aibackend.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Builder
    private Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public void update(String name, Department department) {
        this.name = name;
        this.department = department;
    }
}
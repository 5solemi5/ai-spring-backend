package com.sesac.aibackend.dto;

import com.sesac.aibackend.domain.Department;

public record DepartmentRequest(
        String name
) {
    public Department toEntity() {
        return Department.builder()
                .name(name)
                .build();
    }
}
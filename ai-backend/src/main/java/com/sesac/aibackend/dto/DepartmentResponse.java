package com.sesac.aibackend.dto;

import com.sesac.aibackend.domain.Department;

public record DepartmentResponse(
        Long id,
        String name
) {
    public static DepartmentResponse from(Department department) {
        return new DepartmentResponse(
                department.getId(),
                department.getName()
        );
    }
}